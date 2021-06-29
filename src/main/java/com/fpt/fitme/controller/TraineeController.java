package com.fpt.fitme.controller;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.entity.plan.Plan;
import com.fpt.fitme.entity.plan.PlanMeal;
import com.fpt.fitme.entity.plan.PlanWorkout;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.target.Target;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/trainee")
public class TraineeController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private PlanMealRepository planMealRepository;

    @Autowired
    private PlanWorkoutRepository planWorkoutRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private Environment env;

    @PostMapping("/buildPlan")
    public ResponseEntity buildPlan(@PathVariable Long traineeId, @RequestBody Target target) {
        try {
            Optional<AppUser> optionalTrainee = appUserRepository.findById(traineeId);

            if (optionalTrainee.isPresent()) {
                AppUser trainee = optionalTrainee.get();
                float startingBMI = target.getStartingBMI();
                float targetBMI = target.getTargetBMI();
                float heightInMeter = trainee.getHeight() / 100;
                float weightInKg = (float) (startingBMI * Math.pow(heightInMeter, 2));
                long durationInDays = TimeUnit.DAYS.convert(
                        target.getCompleteDate().getTime() - (new Date()).getTime()
                        , TimeUnit.MILLISECONDS);

                int caloriesPerDay;

                float kgChangeRatePerMonth = (float) (Math.abs(targetBMI - startingBMI) * Math.pow(heightInMeter, 2))
                        / (durationInDays * 30);

                Long exerciseFrequency = trainee.getExerciseFrequencyType();
                int caloriesFromActivitiesPerDay;
                float kgChangeFromActivities;
                if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_UNDER_3_TIMES_PER_WEEK")) == exerciseFrequency) {
                    // sinh hoat binh thuong k tap the duc
                    caloriesFromActivitiesPerDay = 300;
                    kgChangeFromActivities = 0;
                } else if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_3_TIMES_PER_WEEK")) == exerciseFrequency) {
                    caloriesFromActivitiesPerDay = 500;
                    kgChangeFromActivities = 1.2f;
                } else if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_4_TIMES_PER_WEEK")) == exerciseFrequency) {
                    caloriesFromActivitiesPerDay = 600;
                    kgChangeFromActivities = 1.6f;
                } else if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_5_TIMES_PER_WEEK")) == exerciseFrequency) {
                    caloriesFromActivitiesPerDay = 700;
                    kgChangeFromActivities = 2f;
                } else {
                    caloriesFromActivitiesPerDay = 900;
                    kgChangeFromActivities = 2.8f;
                }

                if (targetBMI > startingBMI) {
                    kgChangeRatePerMonth -= kgChangeFromActivities;
                } else {
                    kgChangeRatePerMonth += kgChangeFromActivities;
                }

                caloriesPerDay = calculateBasalCaloriesPerDay(weightInKg, heightInMeter, trainee.getAge(), trainee.getGender())
                        + caloriesFromActivitiesPerDay;

                //Increase weight
                if (kgChangeRatePerMonth >= 2 && targetBMI > startingBMI) {
                    caloriesPerDay = Math.round(caloriesPerDay * Float.parseFloat(env.getProperty("com.fpt.fitme.plan.pace.INCREASE_WEIGHT")));
                }
                // Maintain weight
                else if (kgChangeRatePerMonth < 1) {

                }
                //Mild weight loss
                else if (kgChangeRatePerMonth < 2 && kgChangeRatePerMonth >= 1 && targetBMI < startingBMI) {
                    caloriesPerDay = Math.round(caloriesPerDay * Float.parseFloat(env.getProperty("com.fpt.fitme.plan.pace.MILD_WEIGHT_LOSS")));
                }
                //Weight loss
                else if (kgChangeRatePerMonth < 4 && kgChangeRatePerMonth >= 2 && targetBMI < startingBMI) {
                    caloriesPerDay = Math.round(caloriesPerDay * Float.parseFloat(env.getProperty("com.fpt.fitme.plan.pace.WEIGHT_LOSS")));
                }
                //Fast weight loss
                else {
                    caloriesPerDay = Math.round(caloriesPerDay * Float.parseFloat(env.getProperty("com.fpt.fitme.plan.pace.FAST_WEIGHT_LOSS")));
                }

                //Save target
                target.setHasFinished(false);
                target.setTrainee(trainee);
                targetRepository.save(target);

                //Generate plan for each week
                int numberOfWeeks = Math.round(durationInDays / 7);
                for (int i = 0; i < numberOfWeeks; i++) {
                    int workoutIndex = 0;

                    // Plan meal
                    Long dietPreference = trainee.getDietPreferenceType();
                    // Bua sang
                    List<Meal> breakfasts = getMealsInPeriod(dietPreference, 4L);
                    // Bua trua
                    List<Meal> lunch = getMealsInPeriod(dietPreference, 5L);
                    // Bua toi
                    List<Meal> diners = getMealsInPeriod(dietPreference, 7L);

                    //Plan workout
                    Iterable<Workout> workoutsAllIter = workoutRepository.findAll();
                    List<Workout> workoutsAll = new ArrayList<>();
                    for (Workout workout : workoutsAllIter) {
                        workoutsAll.add(workout);
                    }
                    Collections.shuffle(workoutsAll);

                    for (int y = 0; y < 7; y++) {
                        Long planDate = target.getStartDate().getTime() + i * 7 * 24 * 3600 * 1000 + y * 24 * 3600 * 1000;

                        if (planDate < target.getCompleteDate().getTime()) {
                            Plan plan = new Plan();
                            plan.setDate(new Date(planDate));
                            plan.setTarget(target);
                            planRepository.save(plan);

                            //save bua sang
                            saveMealPlan(plan, breakfasts.get(y));
                            saveMealPlan(plan, lunch.get(y));
                            saveMealPlan(plan, diners.get(y));

                            List<Workout> workouts = new ArrayList<>();
                            int totalWorkoutCalories = 0;

                            while (caloriesFromActivitiesPerDay - totalWorkoutCalories > 150) {
                                Workout workoutToAdd = workoutsAll.get(y + workoutIndex);
                                workouts.add(workoutToAdd);
                                for (Exercise exercise : workoutToAdd.getExercises()) {
                                    totalWorkoutCalories += exercise.getBaseCaloriesPerRound();
                                }
                                workoutIndex++;

                                PlanWorkout planWorkout = new PlanWorkout();
                                planWorkout.setPlan(plan);
                                planWorkout.setWorkout(workoutToAdd);
                                planWorkout.setIsFinished(false);
                                planWorkout.setHasSkipped(false);
                                planWorkoutRepository.save(planWorkout);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @PostMapping("/favoriteMeal?{mealId}")
    public ResponseEntity favoriteMeal(@PathVariable Long mealId) {
        return null;
    }

    @PostMapping("/favoriteWorkout?{workoutId}")
    public ResponseEntity favoriteWorkout(@PathVariable Long workoutId) {
        return null;
    }

    @PostMapping("/completeSurvey")
    public ResponseEntity completeSurvey(@RequestBody AppUser appUser) {
        try {

        } catch (Exception e) {

        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AppUser appUser) {
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
            AppUser savedAppUser = appUserRepository.save(appUser);
            return new ResponseEntity(savedAppUser, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                return new ResponseEntity("Username is duplicated", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int calculateBasalCaloriesPerDay(float weightKg, float heightMeter, int age, int gender) {
        // https://www.calculator.net/calorie-calculator.html?ctype=metric&cage=25&csex=m&cheightfeet=5&cheightinch=10&cpound=165&cheightmeter=180&ckg=65&cactivity=1.465&cmop=1&coutunit=c&cformula=m&cfatpct=20&printit=0&x=101&y=33
        if (gender == Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.GENDER_MALE"))) {
            return (int) (10 * weightKg + 6.25 * heightMeter * 100 - 5 * age + 5);
        } else {
            return (int) (10 * weightKg + 6.25 * heightMeter * 100 - 5 * age - 161);
        }
    }

    private List<Meal> getMealsInPeriod(Long dietPreference, Long mealPeriod) {
        Set<Tag> mealTags = new HashSet<>();
        mealTags.add(tagRepository.findById(dietPreference).get());
        //Bua sang
        mealTags.add(tagRepository.findById(mealPeriod).get());
        List<Meal> meals = mealRepository.findMealsByTagsIn(mealTags);
        Collections.shuffle(meals);
        return meals;
    }

    private void saveMealPlan(Plan plan, Meal meal) {
        PlanMeal planMeal = new PlanMeal();
        planMeal.setPlan(plan);
        planMeal.setMeal(meal);
        planMeal.setHasSkipped(false);
        planMeal.setIsFinished(false);
        planMealRepository.save(planMeal);
    }
}

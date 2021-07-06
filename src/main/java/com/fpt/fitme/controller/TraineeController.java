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
import com.fpt.fitme.entity.workout.WorkoutExercise;
import com.fpt.fitme.model.SurveyCompletionRequest;
import com.fpt.fitme.repository.*;
import com.fpt.fitme.service.FitmeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/trainee")
public class TraineeController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private FitmeUserDetailsService fitmeUserDetailsService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TargetRepository targetRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private PlanMealRepository planMealRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private PlanWorkoutRepository planWorkoutRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private Environment env;

    @PostMapping("/buySubscription")
    public ResponseEntity buySubscription() {
        String email;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                email = ((UserDetails)principal).getUsername();
                AppUser appUser = appUserRepository.getAppUserByEmail(email);
                if (appUser.getIsPremium()) {
                    return new ResponseEntity("User already subscribed", HttpStatus.BAD_REQUEST);
                }
                appUser.setIsPremium(true);
                appUserRepository.save(appUser);
            } else {
                return new ResponseEntity("Invalid credentials", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity("Error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(fitmeUserDetailsService.getUserInfo(email), HttpStatus.OK);
    }

    @PostMapping("/completeSurvey")
    public ResponseEntity<?> updateAppUserOncompleteSurvey(@RequestBody SurveyCompletionRequest request) {
        String email;
        List<Plan> plans = null;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                email = ((UserDetails)principal).getUsername();
                AppUser appUser = appUserRepository.getAppUserByEmail(email);
                appUser.setGender(request.getGender());
                appUser.setAge(request.getAge());
                appUser.setHeight(request.getHeightInCm());
                appUser.setDietPreferenceType(request.getDietPreferenceType());
                appUser.setExerciseFrequencyType(request.getExerciseFrequencyType());
                appUserRepository.save(appUser);
                //handle build plan with durationInDays, targetWeight, age, gender, height, currentWeight
                plans = buildPlan(appUser, request.getWeightInKg(), request.getTargetWeightInKg(), request.getDurationInDays());
            } else {
                return new ResponseEntity("Invalid credentials", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity("Error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("Succeed", HttpStatus.OK);
    }

    private List<Plan> buildPlan(AppUser trainee, float currentWeight, float targetWeight, int durationInDays) {
        Target targetToSave;
        List<Plan> results = null;

        try {
            float heightInMeter = trainee.getHeight() / 100;
            float startingBMI = currentWeight / (float) Math.pow(heightInMeter, 2);
            float targetBMI = targetWeight / (float) Math.pow(heightInMeter, 2);
            int caloriesPerDay;

            float kgChangeRatePerMonth = Math.abs(targetWeight - currentWeight) / (durationInDays / 30);

            int caloriesFromActivitiesPerDay;
            int[] workoutDays;

            long exerciseFrequencyType = trainee.getExerciseFrequencyType();
            if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_UNDER_3_TIMES_PER_WEEK")) == exerciseFrequencyType) {
                // sinh hoat binh thuong k tap the duc
                caloriesFromActivitiesPerDay = 500;
                workoutDays = new int[]{1, 3};
            } else if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_3_TIMES_PER_WEEK")) == exerciseFrequencyType) {
                caloriesFromActivitiesPerDay = 400;
                workoutDays = new int[]{0, 2, 4};
            } else if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_4_TIMES_PER_WEEK")) == exerciseFrequencyType) {
                caloriesFromActivitiesPerDay = 300;
                workoutDays = new int[]{0, 2, 4, 5};
            } else if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_5_TIMES_PER_WEEK")) == exerciseFrequencyType) {
                caloriesFromActivitiesPerDay = 300;
                workoutDays = new int[]{0, 1, 3, 4, 5};
            } else {
                caloriesFromActivitiesPerDay = 300;
                workoutDays = new int[]{0, 1, 2, 3, 4, 5};
            }

            caloriesPerDay = calculateBasalCaloriesPerDay(currentWeight, heightInMeter,
                    trainee.getAge(), trainee.getGender())
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
            targetToSave = new Target();
            targetToSave.setTrainee(trainee);
            targetToSave.setStartingBMI(startingBMI);
            targetToSave.setTargetBMI(targetBMI);
            Long startDateTime = new Date().getTime();
            targetToSave.setStartDate(new Date(startDateTime));
            Long completeDateTime = startDateTime + TimeUnit.DAYS.toMillis(durationInDays);
            targetToSave.setCompleteDate(new Date(completeDateTime));
            targetToSave.setHasFinished(false);
            targetRepository.save(targetToSave);

            //Generate plan for each week
            int numberOfWeeks = Math.round(durationInDays / 7);
            results = new ArrayList<>();
            for (int weekIndex = 0; weekIndex < numberOfWeeks; weekIndex++) {
                int workoutIndex = 0;

                // Plan meal
                Long dietPreference = trainee.getDietPreferenceType();
                // Bua sang
                List<Meal> breakfasts = getMealsInPeriod(dietPreference, 4L, (int) (caloriesPerDay * 0.4));

                // Bua trua
                List<Meal> lunch = getMealsInPeriod(dietPreference, 5L, (int) (caloriesPerDay * 0.25));
                // Bua toi
                List<Meal> diners = getMealsInPeriod(dietPreference, 7L, (int) (caloriesPerDay * 0.2));

                //Plan workout
                Iterable<Workout> workoutsAllIter = workoutRepository.findAll();
                List<Workout> workoutsAll = new ArrayList<>();
                for (Workout workout : workoutsAllIter) {
                    workoutsAll.add(workout);
                }
                Collections.shuffle(workoutsAll);

                for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                    Long planDateTime = startDateTime + TimeUnit.DAYS.toMillis(weekIndex * 7 + dayIndex);

                    if (planDateTime < completeDateTime) {
                        Plan plan = new Plan();
                        plan.setDate(new Date(planDateTime));
                        plan.setTarget(targetToSave);
                        planRepository.save(plan);

                        //save bua sang
                        saveMealPlan(plan, breakfasts.get(dayIndex));
                        saveMealPlan(plan, lunch.get(dayIndex));
                        saveMealPlan(plan, diners.get(dayIndex));

                        List<Workout> workouts = new ArrayList<>();
                        int totalWorkoutCalories = 0;

                        while (caloriesFromActivitiesPerDay - totalWorkoutCalories > 150 && isHaving(workoutDays, dayIndex)) {
                            Workout workoutToAdd = workoutsAll.get(workoutIndex);
                            workouts.add(workoutToAdd);
                            for (WorkoutExercise workoutExercise : workoutToAdd.getWorkout_exercises()) {
                                Exercise exercise = workoutExercise.getExerciseID();
                                totalWorkoutCalories += exercise.getBaseKcal();
                            }
                            workoutIndex++;

                            PlanWorkout planWorkout = new PlanWorkout();
                            planWorkout.setPlan(plan);
                            planWorkout.setWorkout(workoutToAdd);
                            planWorkout.setIsFinished(false);
                            planWorkout.setHasSkipped(false);
                            planWorkoutRepository.save(planWorkout);
                        }

                        results.add(plan);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    private boolean isHaving(int[] arr, int value) {
        for(int i : arr) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    private int calculateBasalCaloriesPerDay(float weightKg, float heightMeter, int age, int gender) {
        // https://www.calculator.net/calorie-calculator.html?ctype=metric&cage=25&csex=m&cheightfeet=5&cheightinch=10&cpound=165&cheightmeter=180&ckg=65&cactivity=1.465&cmop=1&coutunit=c&cformula=m&cfatpct=20&printit=0&x=101&y=33
        if (gender == Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.GENDER_MALE"))) {
            return (int) (10 * weightKg + 6.25 * heightMeter * 100 - 5 * age + 5);
        } else {
            return (int) (10 * weightKg + 6.25 * heightMeter * 100 - 5 * age - 161);
        }
    }

    private List<Meal> getMealsInPeriod(Long dietPreference, Long mealPeriod, int minCalo) {
        Set<Tag> mealTags = new HashSet<>();
        mealTags.add(tagRepository.findById(mealPeriod).get());
        List<Meal> meals = mealRepository.findMealsByTagsIn(mealTags);
        List<Meal> filteredMeals = new ArrayList<>();
        for (Meal meal : meals) {
            if (meal.getCalories() >= minCalo &&
                    (meal.getTags().contains(tagRepository.findById(dietPreference).get()) || dietPreference == 0)) {
                filteredMeals.add(meal);
            }
        }
        meals = new ArrayList<>(filteredMeals);
        if (filteredMeals.size() < 7) {
            int lastIndex = filteredMeals.size() - 1;
            int index = 0;
            for (int i = 0; i < (7 - filteredMeals.size()); i++) {
                meals.add(filteredMeals.get(index));
                index++;
                if (index == lastIndex || lastIndex == 0) {
                    index = 0;
                }
            }
        }
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

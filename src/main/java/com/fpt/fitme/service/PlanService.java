package com.fpt.fitme.service;

import com.fpt.fitme.dto.plan.PlanDTO;
import com.fpt.fitme.dto.plan.PlanMealDTO;
import com.fpt.fitme.dto.plan.PlanWorkoutDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.log.WeightLog;
import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.entity.plan.Plan;
import com.fpt.fitme.entity.plan.PlanMeal;
import com.fpt.fitme.entity.plan.PlanWorkout;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.target.Target;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.repository.*;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class PlanService {

    @Autowired
    private Environment env;

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
    private WeightLogRepository weightLogRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PlanDTO getDailyPlan(AppUser trainee, String dateString) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = simpleDateFormat.parse(dateString);
        Target target = targetRepository.getTargetByTraineeAndHasFinished(trainee, false);
        Plan plan = planRepository.getPlanByDateAndTarget(date, target);
        if (plan == null) {
            throw new NotFoundException("No plan found at date: " + dateString);
        }
        HashMap<Integer, PlanMealDTO> planMealsOrdered = new HashMap<>();
        int index = 0;
        for (PlanMeal planMeal : planMealRepository.getAllByPlanOrderByIdAsc(plan)) {
            planMealsOrdered.put(index, modelMapper.map(planMeal, PlanMealDTO.class));
            index++;
        }
        PlanDTO result = modelMapper.map(plan, PlanDTO.class);
        result.setPlanMeals(planMealsOrdered);
        return result;
    }

    public PlanMealDTO updateMealPlan(AppUser trainee, long planMealId, String status) throws Exception {
        Optional<PlanMeal> optionalPlanMeal = planMealRepository.findById(planMealId);
        Plan toDayPlan = getToDayPlan(trainee);

        if (optionalPlanMeal.isPresent()) {
            PlanMeal planMealToSave = optionalPlanMeal.get();
            if (!toDayPlan.getPlanMeals().contains(planMealToSave)) {
                throw new Exception("Cannot not update a plan that is not today");
            }
            planMealToSave.setStatus(status);
            planMealRepository.save(planMealToSave);
            return modelMapper.map(planMealToSave, PlanMealDTO.class);
        }
        throw new NotFoundException("No meal plan found");
    }

    public PlanWorkoutDTO updateWorkoutPlan(AppUser trainee, long workoutPlanId, String status) throws Exception {
        Optional<PlanWorkout> optionalPlanWorkout = planWorkoutRepository.findById(workoutPlanId);
        Plan toDayPlan = getToDayPlan(trainee);
        if (optionalPlanWorkout.isPresent()) {
            PlanWorkout planWorkoutToSave = optionalPlanWorkout.get();
            if (!toDayPlan.getPlanWorkouts().contains(planWorkoutToSave)) {
                throw new Exception("Cannot not update a plan that is not today");
            }
            planWorkoutToSave.setStatus(status);
            planWorkoutRepository.save(planWorkoutToSave);
            return modelMapper.map(planWorkoutToSave, PlanWorkoutDTO.class);
        }
        throw new NotFoundException("No workout plan found");
    }

    public void buildPlan(AppUser trainee, float currentWeight, float targetWeight, int durationInDays) throws Exception {
        Target targetToSave;

        try {
            float heightInMeter = trainee.getHeight() / 100;
            float startingBMI = currentWeight / (float) Math.pow(heightInMeter, 2);
            float targetBMI = targetWeight / (float) Math.pow(heightInMeter, 2);
            int caloriesPerDay;

            WeightLog firstWeightLog = new WeightLog();
            firstWeightLog.setTrainee(trainee);
            firstWeightLog.setCreatedAt(new Date());
            firstWeightLog.setValue(currentWeight);
            weightLogRepository.save(firstWeightLog);

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
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            targetToSave = new Target();
            targetToSave.setTrainee(trainee);
            targetToSave.setStartingBMI(startingBMI);
            targetToSave.setTargetBMI(targetBMI);
            Date startDate = formatter.parse(formatter.format(new Date()));
            targetToSave.setStartDate(startDate);
            Long completeDateTime = startDate.getTime() + TimeUnit.DAYS.toMillis(durationInDays);
            targetToSave.setCompleteDate(new Date(completeDateTime));
            targetToSave.setHasFinished(false);
            targetRepository.save(targetToSave);

            //Generate plan for each week
            int numberOfWeeks = Math.round(durationInDays / 7);
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
                Iterable<Workout> workoutsAllIter = workoutRepository.getWorkoutByIsPremiumAndIsActive(false, true);
                List<Workout> workoutsAll = new ArrayList<>();
                for (Workout workout : workoutsAllIter) {
                    workoutsAll.add(workout);
                }
                Collections.shuffle(workoutsAll);

                for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                    Long planDateTime = startDate.getTime() + TimeUnit.DAYS.toMillis(weekIndex * 7 + dayIndex);

                    if (planDateTime < completeDateTime) {
                        Plan plan = new Plan();
                        plan.setDate(new Date(planDateTime));
                        plan.setTarget(targetToSave);
                        planRepository.save(plan);

                        //save bua sang
                        saveMealPlan(plan, breakfasts.get(dayIndex));
                        saveMealPlan(plan, lunch.get(dayIndex));
                        saveMealPlan(plan, diners.get(dayIndex));

                        //int totalWorkoutCalories = 0;

                        if (isHaving(workoutDays, dayIndex)) {
                            Workout workoutToAdd = workoutsAll.get(workoutIndex);
                            workoutIndex++;

                            PlanWorkout planWorkout = new PlanWorkout();
                            planWorkout.setPlan(plan);
                            planWorkout.setWorkout(workoutToAdd);
                            planWorkout.setStatus(null);
                            planWorkoutRepository.save(planWorkout);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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
        List<Meal> meals = mealRepository.findMealsByTagsInAndIsActive(mealTags, true);
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
        planMeal.setStatus(null);
        planMealRepository.save(planMeal);
    }

    private Plan getToDayPlan(AppUser trainee) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date now = new Date();
        String dateOnly = simpleDateFormat.format(now);
        Date date = simpleDateFormat.parse(dateOnly);
        Target target = targetRepository.getTargetByTrainee(trainee);
        Plan plan = planRepository.getPlanByDateAndTarget(date, target);
        if (plan == null) {
            throw new NotFoundException("No plan found today: ");
        }
        return plan;
    }
}

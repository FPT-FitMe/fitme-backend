package com.fpt.fitme.controller;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.plan.Plan;
import com.fpt.fitme.entity.plan.PlanMeal;
import com.fpt.fitme.entity.plan.PlanWorkout;
import com.fpt.fitme.entity.target.Target;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.repository.PlanRepository;
import com.fpt.fitme.repository.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    private Environment env;

    @PostMapping("/buildPlan")
    public ResponseEntity buildPlan(@PathVariable Long traineeId, @RequestBody Target target) {
        try {
            List<PlanMeal> planMeals;
            List<PlanWorkout> planWorkouts;
            Optional<AppUser> optionalTrainee = appUserRepository.findById(traineeId);

            if (optionalTrainee.isPresent()) {
                AppUser trainee = optionalTrainee.get();
                float startingBMI = target.getStartingBMI();
                float targetBMI = target.getTargetBMI();
                float height = trainee.getHeight() / 100;
                long durationInDays = TimeUnit.DAYS.convert(
                        target.getCompleteDate().getTime() - (new Date()).getTime()
                        , TimeUnit.MILLISECONDS);

                int caloriesPerDay;

                float kgChangeRatePerMonth = (float) (Math.abs(targetBMI - startingBMI) * Math.pow(height, 2))
                        / (durationInDays * 30);

                int exerciseFrequency = trainee.getExerciseFrequencyType();
                float kgChangeInExercise;
                if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_UNDER_3_TIMES_PER_WEEK")) == exerciseFrequency) {
                    kgChangeInExercise = 0;
                } else if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_3_TIMES_PER_WEEK")) == exerciseFrequency) {
                    kgChangeInExercise = 0.8f;
                } else if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_4_TIMES_PER_WEEK")) == exerciseFrequency) {
                    kgChangeInExercise = 1.2f;
                } else if (Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.EXERCISE_FREQUENCY_5_TIMES_PER_WEEK")) == exerciseFrequency) {
                    kgChangeInExercise = 1.6f;
                } else {
                    kgChangeInExercise = 2.4f;
                }

                if (targetBMI > startingBMI) {
                    kgChangeRatePerMonth -= kgChangeInExercise;
                } else {
                    kgChangeRatePerMonth += kgChangeInExercise;
                }

                //Increase weight
                if (kgChangeRatePerMonth > 2 && targetBMI > startingBMI) {
                    caloriesPerDay = cal
                }
                // Maintain weight
                if (kgChangeRatePerMonth < 2) {

                }
                if (kgChangeRatePerMonth < 4 && targetBMI < startingBMI) {
                    caloriesPerMeal = Math.round(startingBMI)
                }

                //Save target
                target.setHasFinished(false);
                target.setTrainee(trainee);
                targetRepository.save(target);

                //Generate plan for each week
                int numberOfWeeks = Math.round(durationInDays / 7);
                for (int i = 0; i < numberOfWeeks; i++) {

                }
                Plan plan = new Plan();



                plan.s



                // Giam can nhe

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

    private int calculateCaloriesPerDay(float weight, float height, int age, int gender) {
        // https://www.calculator.net/calorie-calculator.html?ctype=metric&cage=25&csex=m&cheightfeet=5&cheightinch=10&cpound=165&cheightmeter=180&ckg=65&cactivity=1.465&cmop=1&coutunit=c&cformula=m&cfatpct=20&printit=0&x=101&y=33
        if (gender == Integer.parseInt(env.getProperty("com.fpt.fitme.entity.appuser.GENDER_MALE"))) {
            return (int) (10 * weight + 6.25 * height - 5 * age + 5);
        } else {
            return (int) (10 * weight + 6.25 * height - 5 * age - 161);
        }
    }
}

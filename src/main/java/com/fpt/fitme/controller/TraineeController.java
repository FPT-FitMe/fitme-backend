package com.fpt.fitme.controller;

import com.fpt.fitme.dto.appUser.AppUserDTO;
import com.fpt.fitme.dto.log.AllLogsDTO;
import com.fpt.fitme.dto.log.MealLogDTO;
import com.fpt.fitme.dto.log.WeightLogDTO;
import com.fpt.fitme.dto.log.WorkoutLogDTO;

import com.fpt.fitme.dto.plan.PlanDTO;
import com.fpt.fitme.dto.plan.PlanMealDTO;
import com.fpt.fitme.dto.plan.PlanWorkoutDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.log.WeightLog;
import com.fpt.fitme.entity.log.WorkoutLog;
import com.fpt.fitme.model.PlanStatusRequest;
import com.fpt.fitme.model.SurveyCompletionRequest;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.service.FitmeUserDetailsService;
import com.fpt.fitme.service.LogService;
import com.fpt.fitme.service.PlanService;
import com.fpt.fitme.service.TraineeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/trainee")
public class TraineeController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private FitmeUserDetailsService fitmeUserDetailsService;

    @Autowired
    private PlanService planService;

    @Autowired
    private TraineeService traineeService;

    @Autowired
    private LogService logService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/buySubscription")
    public ResponseEntity<?> buySubscription() {
        try {
            AppUser appUser = fitmeUserDetailsService.getUserByAuthorization();
            if (appUser.getIsPremium()) {
                return new ResponseEntity<>("User already subscribed", HttpStatus.BAD_REQUEST);
            }
            appUser.setIsPremium(true);
            appUserRepository.save(appUser);
            return new ResponseEntity<>(modelMapper.map(appUser, AppUserDTO.class), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logMeal/{mealId}")
    public ResponseEntity<?> logMeal(@PathVariable long mealId) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();
            MealLogDTO mealLogDTO = logService.createMealLog(mealId, trainee);
            return new ResponseEntity<>(mealLogDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logWorkout/{workoutId}")
    public ResponseEntity<?> logWorkout(@PathVariable long workoutId, @RequestBody WorkoutLog workoutLog) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();
            WorkoutLogDTO workoutLogDTO = logService.createWorkoutLog(workoutId, trainee, workoutLog);
            return new ResponseEntity<>(workoutLogDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logWeight")
    public ResponseEntity<?> logWeight(@RequestBody WeightLog weightLog) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();
            WeightLogDTO weightLogDTO = logService.createWeightLog(weightLog, trainee);
            return new ResponseEntity<>(weightLogDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/log/{type}/{date}")
    public ResponseEntity<?> getLogByDateAndType(@PathVariable String type, @PathVariable String date) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();

            switch (type) {
                case "workout":
                    Set<WorkoutLogDTO> workoutLogDTOS = logService.getAllWorkoutLogsByDate(trainee, date);
                    return new ResponseEntity<>(workoutLogDTOS, HttpStatus.OK);
                case "meal":
                    Set<MealLogDTO> mealLogDTOS = logService.getAllMealLogsByDate(trainee, date);
                    return new ResponseEntity<>(mealLogDTOS, HttpStatus.OK);
                case "weight":
                    Set<WeightLogDTO> weightLogDTOS = logService.getAllWeightLogsByDate(trainee, date);
                    return new ResponseEntity<>(weightLogDTOS, HttpStatus.OK);
                case "all":
                    AllLogsDTO allLogsDTO = new AllLogsDTO();
                    allLogsDTO.setWorkoutLogs(logService.getAllWorkoutLogsByDate(trainee, date));
                    allLogsDTO.setMealLogs(logService.getAllMealLogsByDate(trainee, date));
                    allLogsDTO.setWeightLogs(logService.getAllWeightLogsByDate(trainee, date));
                    return new ResponseEntity<>(allLogsDTO, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
      
    @PostMapping("favorite/{type}/{id}")
    public ResponseEntity<?> favoriteItem(@PathVariable String type, @PathVariable long id) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();
            if (type.equals("meal")) {
                traineeService.favoriteMeal(trainee, id);
                return new ResponseEntity<>("Succeed", HttpStatus.OK);
            } else if (type.equals("workout")) {
                traineeService.favoriteWorkout(trainee, id);
                return new ResponseEntity<>("Succeed", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("unFavorite/{type}/{id}")
    public ResponseEntity<?> unFavoriteItem(@PathVariable String type, @PathVariable long id) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();
            if (type.equals("meal")) {
                traineeService.unFavoriteMeal(trainee, id);
                return new ResponseEntity<>("Succeed", HttpStatus.OK);
            } else if (type.equals("workout")) {
                traineeService.unFavoriteWorkout(trainee, id);
                return new ResponseEntity<>("Succeed", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("favorite/{type}")
    public ResponseEntity<?> getAllFavorite(@PathVariable String type) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();
            if (type.equals("meal")) {
                return new ResponseEntity<>(traineeService.getAllFavoriteMeal(trainee), HttpStatus.OK);
            } else if (type.equals("workout")) {
                return new ResponseEntity<>(traineeService.getAllFavoriteWorkout(trainee), HttpStatus.OK);
            } else if (type.equals("all")) {
                return new ResponseEntity<>(traineeService.getAllFavorite(trainee), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/completeSurvey")
    public ResponseEntity<?> updateAppUserOncompleteSurvey(@RequestBody SurveyCompletionRequest request) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();

            trainee.setGender(request.getGender());
            trainee.setAge(request.getAge());
            trainee.setHeight(request.getHeightInCm());
            trainee.setDietPreferenceType(request.getDietPreferenceType());
            trainee.setExerciseFrequencyType(request.getExerciseFrequencyType());
            appUserRepository.save(trainee);
            //handle build plan with durationInDays, targetWeight, age, gender, height, currentWeight
            planService.buildPlan(trainee, request.getWeightInKg(), request.getTargetWeightInKg(), request.getDurationInDays());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Succeed", HttpStatus.OK);
    }


    @GetMapping("/dailyPlan/{date}")
    public ResponseEntity<?> getDailyPlan(@PathVariable String date) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();
            PlanDTO planDTO = planService.getDailyPlan(trainee, date);
            return new ResponseEntity<>(planDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updatePlanMeal/{planMealId}")
    public ResponseEntity<?> updatePlanMeal(@PathVariable long planMealId, @RequestBody PlanStatusRequest request) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();
            PlanMealDTO updateMealPlan = planService.updateMealPlan(trainee, planMealId, request.getStatus());
            return new ResponseEntity<>(updateMealPlan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updatePlanWorkout/{planWorkoutId}")
    public ResponseEntity<?> updatePlanWorkout(@PathVariable long planWorkoutId, @RequestBody PlanStatusRequest request) {
        try {
            AppUser trainee = fitmeUserDetailsService.getUserByAuthorization();
            PlanWorkoutDTO updateWorkoutPlan = planService.updateWorkoutPlan(trainee, planWorkoutId, request.getStatus());
            return new ResponseEntity<>(updateWorkoutPlan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



package com.fpt.fitme.controller;

import com.fpt.fitme.dto.appUser.AppUserDTO;
import com.fpt.fitme.dto.log.MealLogDTO;
import com.fpt.fitme.dto.log.WeightLogDTO;
import com.fpt.fitme.dto.log.WorkoutLogDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.log.WeightLog;
import com.fpt.fitme.entity.log.WorkoutLog;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.service.FitmeUserDetailsService;
import com.fpt.fitme.service.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee")
public class TraineeController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private FitmeUserDetailsService fitmeUserDetailsService;

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

}



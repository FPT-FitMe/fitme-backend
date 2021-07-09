package com.fpt.fitme.service;

import com.fpt.fitme.dto.log.MealLogDTO;
import com.fpt.fitme.dto.log.WeightLogDTO;
import com.fpt.fitme.dto.log.WorkoutLogDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.log.MealLog;
import com.fpt.fitme.entity.log.WeightLog;
import com.fpt.fitme.entity.log.WorkoutLog;
import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.repository.*;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class LogService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MealLogRepository mealLogRepository;

    @Autowired
    private WorkoutLogRepository workoutLogRepository;

    @Autowired
    private WeightLogRepository weightLogRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    public MealLogDTO createMealLog(Long mealId, AppUser trainee) throws Exception {
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);
        MealLog mealLogToSave;
        if (optionalMeal.isPresent() && optionalMeal.get().getIsActive()) {
            mealLogToSave = new MealLog();
            mealLogToSave.setMeal(optionalMeal.get());
            mealLogToSave.setCreatedAt(new Date());
            mealLogToSave.setTrainee(trainee);
            mealLogRepository.save(mealLogToSave);
        } else {
            throw new NotFoundException("Meal not found");
        }
        return modelMapper.map(mealLogToSave, MealLogDTO.class);
    }

    public WorkoutLogDTO createWorkoutLog(Long workoutId, AppUser trainee, WorkoutLog workoutLog) throws Exception {
        Optional<Workout> optionalWorkout = workoutRepository.findById(workoutId);
        WorkoutLog workoutLogToSave;
        if (optionalWorkout.isPresent() && optionalWorkout.get().getIsActive()) {
            workoutLogToSave = workoutLog;
            workoutLogToSave.setWorkout(optionalWorkout.get());
            workoutLogToSave.setCreatedAt(new Date());
            workoutLogToSave.setTrainee(trainee);
            workoutLogRepository.save(workoutLogToSave);
            return modelMapper.map(workoutLogToSave, WorkoutLogDTO.class);
        } else {
            throw new NotFoundException("Workout not found");
        }
    }

    public WeightLogDTO createWeightLog(WeightLog weightLog, AppUser trainee) throws Exception {
        weightLog.setTrainee(trainee);
        weightLog.setCreatedAt(new Date());
        weightLogRepository.save(weightLog);
        return modelMapper.map(weightLog, WeightLogDTO.class);
    }

    public Set<WorkoutLogDTO> getAllWorkoutLogsByDate(AppUser trainee, String dateString) throws Exception {
        Set<WorkoutLogDTO> result;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = simpleDateFormat.parse(dateString);
        Date toDate = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
        Set<WorkoutLog> workoutLogs = workoutLogRepository.findAllByCreatedAtBetweenAndTrainee(date, toDate, trainee);
        if (workoutLogs.isEmpty()) {
            throw new NotFoundException("No log found in date: " + simpleDateFormat.format(date));
        }
        result = new HashSet<>();
        for (WorkoutLog workoutLog : workoutLogs) {
            result.add(modelMapper.map(workoutLog, WorkoutLogDTO.class));
        }
        return result;
    }

    public Set<MealLogDTO> getAllMealLogsByDate(AppUser trainee, String dateString) throws Exception {
        Set<MealLogDTO> result;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = simpleDateFormat.parse(dateString);
        Date toDate = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
        Set<MealLog> mealLogs = mealLogRepository.findAllByCreatedAtBetweenAndTrainee(date, toDate, trainee);
        if (mealLogs.isEmpty()) {
            throw new NotFoundException("No log found in date: " + simpleDateFormat.format(date));
        }
        result = new HashSet<>();
        for (MealLog mealLog : mealLogs) {
            result.add(modelMapper.map(mealLog, MealLogDTO.class));
        }
        return result;
    }

    public Set<WeightLogDTO> getAllWeightLogsByDate(AppUser trainee, String dateString) throws Exception {
        Set<WeightLogDTO> result;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = simpleDateFormat.parse(dateString);
        Date toDate = new Date(date.getTime() + TimeUnit.DAYS.toMillis(1));
        Set<WeightLog> weightLogs = weightLogRepository.findAllByCreatedAtBetweenAndTrainee(date, toDate, trainee);
        if (weightLogs.isEmpty()) {
            throw new NotFoundException("No log found in date: " + simpleDateFormat.format(date));
        }
        result = new HashSet<>();
        for (WeightLog weightLog : weightLogs) {
            result.add(modelMapper.map(weightLog, WeightLogDTO.class));
        }
        return result;
    }
}

package com.fpt.fitme.service;

import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.entity.workout.Workout_Exercise;
import com.fpt.fitme.repository.WorkoutExerciseRepository;
import com.fpt.fitme.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    public Workout getWorkoutAfterUpdateKcalandDuration(long workoutID){
        Optional<Workout> workoutOptional=workoutRepository.findById(workoutID);
        if(workoutOptional.isPresent()&&workoutOptional.get().getIsActive()){
            int totalDuration=-15;
            int totalKcal=0;
            List<Workout_Exercise> workout_exerciseList=workoutExerciseRepository.findByWorkoutID(workoutOptional.get());
            for (Workout_Exercise we:workout_exerciseList
                 ) {
                totalDuration+=we.getExerciseID().getBaseDuration()+15;//15s nghi giua hiep
                totalKcal+=we.getExerciseID().getBaseKcal();
            }
            if(workoutRepository.updateDurationCaloriesWorkout(workoutID,totalKcal,totalDuration)>0)
                return workoutRepository.findById(workoutID).get();
        }
        return null;
    }
}

package com.fpt.fitme.controller;

import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.workout.Workout_Exercise;
import com.fpt.fitme.repository.ExerciseRepository;
import com.fpt.fitme.repository.WorkoutExerciseRepository;
import com.fpt.fitme.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workout_exercise")
public class WorkoutExerciseController {
    private static final String ID_NOTFOUND_ERROR = " Check other relation's ID";

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    //get exercise by order in workout
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseByOrder(@PathVariable("id") long id) {
        Optional<Workout_Exercise> workout_exerciseOptional = workoutExerciseRepository.findById(id);

        if (workout_exerciseOptional.isPresent()) {
            return new ResponseEntity(workout_exerciseOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



}

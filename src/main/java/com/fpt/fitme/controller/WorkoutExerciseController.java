package com.fpt.fitme.controller;

import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.workout.WorkoutExercise;
import com.fpt.fitme.repository.ExerciseRepository;
import com.fpt.fitme.repository.WorkoutExerciseRepository;
import com.fpt.fitme.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Optional<WorkoutExercise> workoutExerciseOptional = workoutExerciseRepository.findById(id);

        if (workoutExerciseOptional.isPresent()) {
            return new ResponseEntity(workoutExerciseOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



}

package com.fpt.fitme.controller;

import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.repository.ExerciseRepository;
import com.fpt.fitme.repository.WorkoutRepository;
import com.fpt.fitme.util.JsonPatcherUtil;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @GetMapping("")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> result = new ArrayList<>();
        exerciseRepository.findAll().forEach(result::add);
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseByID(@PathVariable("id") long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);

        if (exerciseOptional.isPresent()) {
            return new ResponseEntity<>(exerciseOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity addExercise(@RequestBody Exercise exercise) {
        try {
            Optional<Workout> workout = workoutRepository.findById(exercise.getWorkout().getWorkoutID());

            if (workout.isPresent()) {
                exercise.setWorkout(workout.get());
                Exercise savedExercise = exerciseRepository.save(exercise);
                return new ResponseEntity(savedExercise, HttpStatus.CREATED);
            }
            return new ResponseEntity("Check other relation's ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchMeal(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            Optional<Exercise> currentExercise = exerciseRepository.findById(id);

            if (currentExercise.isPresent()) {
                Exercise exercisePatched = (Exercise) JsonPatcherUtil.applyPatch(patch, currentExercise.get());
                exerciseRepository.save(exercisePatched);
                return ResponseEntity.ok(exercisePatched);
            }  else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody Exercise exercise) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isPresent()) {
            Exercise exerciseToUpdate = optionalExercise.get();
            exerciseToUpdate.setName(exercise.getName());
            exerciseToUpdate.setDescription(exercise.getDescription());
            exerciseToUpdate.setVideoFile(exercise.getVideoFile());
            exerciseToUpdate.setBaseDuration(exercise.getBaseDuration());
            exerciseToUpdate.setBaseRepPerRound(exercise.getBaseRepPerRound());
            exerciseToUpdate.setImageFile(exercise.getImageFile());
            return new ResponseEntity(exerciseRepository.save(exerciseToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}

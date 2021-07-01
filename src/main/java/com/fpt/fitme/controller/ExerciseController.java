package com.fpt.fitme.controller;

import com.fpt.fitme.dto.Exercise.DisableExerciseDTO;
import com.fpt.fitme.dto.Exercise.ExerciseDTO;
import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.service.ExerciseService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private static final String ID_NOTFOUND_ERROR = " exerciseID Not Found";

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("")
    public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
        List<ExerciseDTO> result = exerciseService.getListExercise();
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> getExerciseByID(@PathVariable("id") long id) {
        ExerciseDTO dto = exerciseService.getExerciseByID(id);

        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //create exercise without workout
    @PostMapping("")
    public ResponseEntity createExercise(@RequestBody Exercise exercise) {
        try {
            ExerciseDTO dto = exerciseService.createExercise(exercise);
            return new ResponseEntity(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //create exercise with workout
    @PostMapping("/workout")
    public ResponseEntity createExerciserWorkout(@RequestParam("Workout_id") long id, @RequestBody Exercise exercise) {
        try {
            ExerciseDTO dto = exerciseService.createExercise(exercise,id);
            return new ResponseEntity(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchExercise(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            DisableExerciseDTO dto = exerciseService.disableExercise(id,patch);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody Exercise exercise) {
       ExerciseDTO dto=exerciseService.updateExercise(id,exercise);
       if(dto!=null){
           return new ResponseEntity(dto, HttpStatus.OK);
       }
        return new ResponseEntity<>(ID_NOTFOUND_ERROR, HttpStatus.BAD_REQUEST);
    }

}
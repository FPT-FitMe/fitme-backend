package com.fpt.fitme.controller;

import com.fpt.fitme.dto.WorkoutExercise.WorkoutExerciseDTO;
import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.service.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout_exercise")
public class WorkoutExerciseController {

    @Autowired
    private WorkoutExerciseService workoutExerciseService;

    //get exercise by order in workout
    @PostMapping("")
    public ResponseEntity<List<WorkoutExerciseDTO>> addExercise(@RequestParam("workoutID") long id, @RequestBody Exercise[] exercises) {
        try {
            List<WorkoutExerciseDTO> result = workoutExerciseService.addListExerciseToWorkout(id, exercises);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("")
    public ResponseEntity<List<WorkoutExerciseDTO>> removeExercise(@RequestParam("workoutID") long workoutID, @RequestParam("exerciseID") long exerciseID, @RequestParam("exerciseOrder") long order) {
        try {
            boolean result = workoutExerciseService.deleteExerciseWorkout(exerciseID, workoutID, order);
            if (result) {
                return new ResponseEntity(workoutExerciseService.getListExerciseByOrder(workoutID), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<WorkoutExerciseDTO>> removeExercise(@PathVariable("id") long workoutID) {
        try {
            List<WorkoutExerciseDTO> result=workoutExerciseService.getListExerciseByOrder(workoutID);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //update Exercise By Order
    @PostMapping("/order")
    public ResponseEntity<List<WorkoutExerciseDTO>> updateExerciseOrder(@RequestParam("Workout_id") long workoutID, @RequestBody Exercise[] exercises) {
        try {
            List<WorkoutExerciseDTO> result = workoutExerciseService.updateExerciseListByOrder(workoutID, exercises);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package com.fpt.fitme.controller;

import com.fpt.fitme.dto.workout.DisableWorkoutDTO;
import com.fpt.fitme.dto.workout.WorkoutDTO;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.service.WorkoutService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    private static final String ID_NOTFOUND_ERROR = "WorkoutID Not Found";

    @Autowired
    private WorkoutService workoutService;



    @GetMapping("")
    public ResponseEntity<List<WorkoutDTO>> getAllWorkout(@RequestParam(required = false,name ="tagID")Long tagID,@RequestParam(required = false,name ="coachID")Long coachID) {
        List<WorkoutDTO> result;
        try {
            if(tagID!=null){
                result=workoutService.getListWorkoutByTagID(tagID);
            }else if(coachID!=null){
                result=workoutService.getListWorkoutByCoachID(coachID);
            }else{
                result = workoutService.getListWorkout();
            }
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity("List Empty!", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDTO> getWorkoutByID(@PathVariable("id") long id) {
        WorkoutDTO dto = workoutService.getWorkoutByID(id);

        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity(ID_NOTFOUND_ERROR, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity createWorkout(@RequestBody Workout workout) {
        try {
            WorkoutDTO dto = workoutService.createWorkout(workout);
            return new ResponseEntity(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchWorkout(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            DisableWorkoutDTO dto = workoutService.disableWorkout(id, patch);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody Workout workout) {
        try {
            WorkoutDTO dto = workoutService.updateWorkout(id, workout);
            if (dto != null) {
                return new ResponseEntity(dto, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ID_NOTFOUND_ERROR, HttpStatus.BAD_REQUEST);
    }
}

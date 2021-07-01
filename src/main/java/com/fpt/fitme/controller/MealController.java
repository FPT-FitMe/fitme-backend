package com.fpt.fitme.controller;

import com.fpt.fitme.dto.Meal.DisableMealDTO;
import com.fpt.fitme.dto.Meal.MealDTO;
import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.service.MealService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/meals")
public class MealController {

    @Autowired
   private MealService mealService;

    @GetMapping("")
    public ResponseEntity<List<MealDTO>> getAllMeals() {
        List<MealDTO> result = mealService.getListMeal();
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealDTO> getMealByID(@PathVariable("id") long id) {
        MealDTO dto= mealService.getMealByID(id);

        if (dto!=null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity createMeal(@RequestBody Meal meal) {
        try {
           MealDTO dto=mealService.createMeal(meal);
           return new ResponseEntity(dto,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchMeal(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            DisableMealDTO dto=mealService.disableMeal(id,patch);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody Meal meal) {
       try {
           MealDTO dto=mealService.updateMeal(id,meal);
           if(dto!=null){
               return new ResponseEntity(dto,HttpStatus.OK);
           }
       }catch (Exception e){
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package com.fpt.fitme.controller;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.repository.MealRepository;
import com.fpt.fitme.repository.TagRepository;
import com.fpt.fitme.util.JsonPatcherUtil;
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
    private MealRepository mealRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("")
    public ResponseEntity<List<Meal>> getAllMeals() {
        List<Meal> result = new ArrayList<>();
        mealRepository.findAll().forEach(result::add);
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealByID(@PathVariable("id") long id) {
        Optional<Meal> mealOptional = mealRepository.findById(id);

        if (mealOptional.isPresent()) {
            return new ResponseEntity<>(mealOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity addMeal(@RequestBody Meal meal) {
        try {
            Optional<AppUser> creator = appUserRepository.findById(meal.getCreator().getUserID());
            Set<Tag> tags = new HashSet<>();
            for (Tag tag : meal.getTags()) {
                Optional<Tag> tagToAdd = tagRepository.findById(tag.getId());
                if (tagToAdd.isPresent()) {
                    tags.add(tagToAdd.get());
                }
            }

            if (creator.isPresent()) {
                meal.setCreator(creator.get());
                meal.setTags(tags);
                Meal savedMeal = mealRepository.save(meal);
                return new ResponseEntity(savedMeal, HttpStatus.CREATED);
            }
            return new ResponseEntity("Check other relation's ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchMeal(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            Optional<Meal> currentMeal = mealRepository.findById(id);

            if (currentMeal.isPresent()) {
                Meal mealPatched = (Meal) JsonPatcherUtil.applyPatch(patch, currentMeal.get());
                mealRepository.save(mealPatched);
                return ResponseEntity.ok(mealPatched);
            }  else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody Meal meal) {
        Optional<Meal> optionalMeal = mealRepository.findById(id);

        if (optionalMeal.isPresent()) {
            Meal mealToUpdate = optionalMeal.get();
            mealToUpdate.setName(meal.getName());
            mealToUpdate.setDescription(meal.getDescription());
            mealToUpdate.setCreator(meal.getCreator());
            mealToUpdate.setTags(meal.getTags());
            mealToUpdate.setCookingTime(meal.getCookingTime());
            mealToUpdate.setIsPremium(meal.getIsPremium());
            mealToUpdate.setImageUrl(meal.getImageUrl());
            mealToUpdate.setCalories(meal.getCalories());
            mealToUpdate.setCarbAmount(meal.getCarbAmount());
            mealToUpdate.setFatAmount(meal.getFatAmount());
            return new ResponseEntity(mealRepository.save(mealToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}

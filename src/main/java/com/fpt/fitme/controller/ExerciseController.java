package com.fpt.fitme.controller;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.workout.CoachProfile;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.entity.workout.Workout_Exercise;
import com.fpt.fitme.repository.*;
import com.fpt.fitme.util.JsonPatcherUtil;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private static final String ID_RELATION_NOTFOUND_ERROR = " Check other relation's ID";
    private static final String ID_NOTFOUND_ERROR = " ID Not Found";

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    @GetMapping("")
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> result = new ArrayList<>();
        exerciseRepository.findAll().forEach(exercise -> {
            if (exercise.getIsActive()) {
                result.add(exercise);
            }
        });
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseByID(@PathVariable("id") long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);

        if (exerciseOptional.isPresent() && exerciseOptional.get().getIsActive()) {
            return new ResponseEntity<>(exerciseOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //create exercise without workout
    @PostMapping("")
    public ResponseEntity createExercise(@RequestBody Exercise exercise) {
        try {
//            if exercise
            Optional<AppUser> creator = appUserRepository.findById(exercise.getCreator().getUserID());
            if (creator.isPresent()) {
                Set<Tag> tags = new HashSet<>();
                for (Tag tag : exercise.getTags()) {
                    Optional<Tag> tagToAdd = tagRepository.findById(tag.getId());
                    if (tagToAdd.isPresent()) {
                        tags.add(tagToAdd.get());
                    }
                }
                exercise.setCreator(creator.get());
                exercise.setTags(tags);
                exercise.setIsActive(true);

                Exercise savedExercise = exerciseRepository.save(exercise);
                return new ResponseEntity(savedExercise, HttpStatus.CREATED);
            }
            return new ResponseEntity(ID_RELATION_NOTFOUND_ERROR, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //create exercise with workout
    @PostMapping("/workout")
    public ResponseEntity createExerciserWorkout(@RequestParam("Workout_id") long id, @RequestBody Exercise exercise) {
        try {
            Optional<AppUser> creator = appUserRepository.findById(exercise.getCreator().getUserID());
            Optional<Workout> workout = workoutRepository.findById(id);
            if (workout.isPresent() && creator.isPresent()) {
                Set<Tag> tags = new HashSet<>();
                for (Tag tag : exercise.getTags()) {
                    Optional<Tag> tagToAdd = tagRepository.findById(tag.getId());
                    if (tagToAdd.isPresent()) {
                        tags.add(tagToAdd.get());
                    }
                }
                exercise.setCreator(creator.get());
                exercise.setTags(tags);
                exercise.setIsActive(true);
                Exercise savedExercise = exerciseRepository.save(exercise);
                if (savedExercise != null) {
                    long quantity = workoutExerciseRepository.countWorkout_ExerciseByWorkoutID(workout.get());
                    Workout_Exercise workout_exercise = new Workout_Exercise();
                    workout_exercise.setExerciseID(savedExercise);
                    workout_exercise.setWorkoutID(workout.get());
                    workout_exercise.setExerciseOrder(quantity + 1);
                    savedExercise.getWorkout_exercises().add(workout_exercise);
                    workoutExerciseRepository.save(workout_exercise);
                    return new ResponseEntity(savedExercise, HttpStatus.CREATED);
                }
            }
            return new ResponseEntity(ID_RELATION_NOTFOUND_ERROR, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchExercise(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            Optional<Exercise> currentExercise = exerciseRepository.findById(id);

            if (currentExercise.isPresent() && currentExercise.get().getIsActive()) {
                Exercise exercisePatched = (Exercise) JsonPatcherUtil.applyPatch(patch, currentExercise.get());
                exerciseRepository.save(exercisePatched);
                return ResponseEntity.ok(exercisePatched);
            }
            return new ResponseEntity<>(ID_NOTFOUND_ERROR, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody Exercise exercise) {
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isPresent()&&optionalExercise.get().getIsActive()) {
            Exercise exerciseToUpdate = optionalExercise.get();
            exerciseToUpdate.setName(exercise.getName());
            exerciseToUpdate.setDescription(exercise.getDescription());
            exerciseToUpdate.setVideoUrl(exercise.getVideoUrl());
            exerciseToUpdate.setTags(exercise.getTags());
            exerciseToUpdate.setBaseDuration(exercise.getBaseDuration());
            exerciseToUpdate.setBaseRepPerRound(exercise.getBaseRepPerRound());
            exerciseToUpdate.setBaseKcal(exercise.getBaseKcal());
            exerciseToUpdate.setImageUrl(exercise.getImageUrl());
            return new ResponseEntity(exerciseRepository.save(exerciseToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
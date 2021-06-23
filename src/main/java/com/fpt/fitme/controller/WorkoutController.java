package com.fpt.fitme.controller;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.workout.CoachProfile;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.repository.CoachProfileRepository;
import com.fpt.fitme.repository.TagRepository;
import com.fpt.fitme.repository.WorkoutRepository;
import com.fpt.fitme.util.JsonPatcherUtil;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private CoachProfileRepository coachProfileRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("")
    public ResponseEntity<List<Workout>> getAllWorkout() {
        List<Workout> result = new ArrayList<>();
        workoutRepository.findAll().forEach(result::add);
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutByID(@PathVariable("id") long id) {
        Optional<Workout> workoutOptional = workoutRepository.findById(id);

        if (workoutOptional.isPresent()) {
            return new ResponseEntity<>(workoutOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity addWorkout(@RequestBody Workout workout) {
        try {
            Optional<CoachProfile> coachProfile = coachProfileRepository.findById(workout.getCoachProfile().getCoachID());
            Optional<AppUser> creator = appUserRepository.findById(workout.getCreator().getUserID());
            Set<Tag> tags = new HashSet<>();
            for (Tag tag : workout.getTags()) {
                Optional<Tag> tagToAdd = tagRepository.findById(tag.getId());
                if (tagToAdd.isPresent()) {
                    tags.add(tagToAdd.get());
                }
            }
            if (coachProfile.isPresent() && creator.isPresent()) {
                workout.setCoachProfile(coachProfile.get());
                workout.setCreator(creator.get());
                workout.setTags(tags);
                Workout savedWorkout = workoutRepository.save(workout);
                return new ResponseEntity(savedWorkout, HttpStatus.CREATED);
            }
            return new ResponseEntity("Check other relation's ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchWorkout(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            Optional<Workout> currentWorkout = workoutRepository.findById(id);

            if (currentWorkout.isPresent()) {
                Workout workoutPatched = (Workout) JsonPatcherUtil.applyPatch(patch, currentWorkout.get());
                workoutRepository.save(workoutPatched);
                return ResponseEntity.ok(workoutPatched);
            }  else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody Workout workout) {
        Optional<Workout> optionalWorkout = workoutRepository.findById(id);

        if (optionalWorkout.isPresent()) {
            Workout workoutToUpdate = optionalWorkout.get();
            workoutToUpdate.setName(workout.getName());
            workoutToUpdate.setCoachProfile(workout.getCoachProfile());
            workoutToUpdate.setCreator(workout.getCreator());
            workoutToUpdate.setDescription(workout.getDescription());
            workoutToUpdate.setTags(workout.getTags());
            workoutToUpdate.setEstimatedDuration(workout.getEstimatedDuration());
            workoutToUpdate.setEstimatedCalories(workout.getEstimatedCalories());
            workoutToUpdate.setLevel(workout.getLevel());
            workoutToUpdate.setIsPremium(workout.getIsPremium());
            workoutToUpdate.setImageUrl(workout.getImageUrl());
            return new ResponseEntity(workoutRepository.save(workoutToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

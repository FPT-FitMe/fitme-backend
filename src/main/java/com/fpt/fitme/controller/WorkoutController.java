package com.fpt.fitme.controller;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.workout.CoachProfile;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.repository.CoachProfileRepository;
import com.fpt.fitme.repository.TagRepository;
import com.fpt.fitme.repository.WorkoutRepository;
import com.fpt.fitme.service.WorkoutService;
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

    private static final String ID_RELATION_NOTFOUND_ERROR = " Check other relation's ID";
    private static final String ID_NOTFOUND_ERROR = " ID Not Found";

    @Autowired
    private WorkoutService workoutService;

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
        workoutRepository.findAll().forEach(workout -> {
            workout=workoutService.getWorkoutAfterUpdateKcalandDuration(workout.getWorkoutID());
            result.add(workout);
        });
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutByID(@PathVariable("id") long id) {
        Workout workout = workoutService.getWorkoutAfterUpdateKcalandDuration(id);

        if (workout!=null) {
            return new ResponseEntity<>(workout, HttpStatus.OK);
        } else {
            return new ResponseEntity(ID_NOTFOUND_ERROR, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity createWorkout(@RequestBody Workout workout) {
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
                workout.setEstimatedCalories(0);
                workout.setEstimatedDuration(0);
                workout.setIsActive(true);
                Workout savedWorkout = workoutRepository.save(workout);
                return new ResponseEntity(savedWorkout, HttpStatus.CREATED);
            }
            return new ResponseEntity(ID_RELATION_NOTFOUND_ERROR, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchWorkout(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            Optional<Workout> currentWorkout = workoutRepository.findById(id);

            if (currentWorkout.isPresent()&&currentWorkout.get().getIsActive()) {
                Workout workoutPatched = (Workout) JsonPatcherUtil.applyPatch(patch, currentWorkout.get());
                workoutRepository.save(workoutPatched);
                return ResponseEntity.ok(workoutPatched);
            }  else {
                return new ResponseEntity<>(ID_NOTFOUND_ERROR, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(ID_NOTFOUND_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
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
            workoutToUpdate.setLevel(workout.getLevel());
            workoutToUpdate.setIsPremium(workout.getIsPremium());
            workoutToUpdate.setImageUrl(workout.getImageUrl());
            return new ResponseEntity(workoutRepository.save(workoutToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ID_NOTFOUND_ERROR, HttpStatus.NOT_FOUND);
        }
    }
}

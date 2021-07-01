package com.fpt.fitme.service;

import com.fpt.fitme.dto.Exercise.ExerciseDTO;
import com.fpt.fitme.dto.Workout.DisableWorkoutDTO;
import com.fpt.fitme.dto.Workout.WorkoutDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.workout.CoachProfile;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.entity.workout.Workout_Exercise;
import com.fpt.fitme.repository.*;
import com.fpt.fitme.util.JsonPatcherUtil;
import com.github.fge.jsonpatch.JsonPatch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkoutService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private CoachProfileRepository coachProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<WorkoutDTO> getListWorkout() {
        List<WorkoutDTO> result = new ArrayList<>();
        workoutRepository.findAll().forEach(workout -> {
            if (workout.getIsActive()) {
                WorkoutDTO dto = modelMapper.map(workout, WorkoutDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public WorkoutDTO getWorkoutByID(long id) {
        Optional<Workout> workoutOptional = workoutRepository.findById(id);
        if (workoutOptional.isPresent() && workoutOptional.get().getIsActive())
            return modelMapper.map(workoutOptional.get(), WorkoutDTO.class);
        return null;
    }

    public WorkoutDTO createWorkout(Workout workout) {
        Optional<AppUser> creator = appUserRepository.findById(workout.getCreator().getUserID());
        Optional<CoachProfile> coachProfile = coachProfileRepository.findById(workout.getCoachProfile().getCoachID());

        if (!coachProfile.isPresent()) throw new IllegalArgumentException("coachID not found!");

        if (!creator.isPresent()) throw new IllegalArgumentException("creatorID not found!");

        Set<Tag> tags = new HashSet<>();
        for (Tag tag : workout.getTags()) {
            Optional<Tag> tagToAdd = tagRepository.findById(tag.getId());
            if (tagToAdd.isPresent()) {
                tags.add(tagToAdd.get());
            }
        }

        workout.setCoachProfile(coachProfile.get());
        workout.setCreator(creator.get());
        workout.setTags(tags);
        workout.setEstimatedCalories(0);
        workout.setEstimatedDuration(0);
        workout.setIsActive(true);

        Workout savedWorkout = workoutRepository.save(workout);
        return modelMapper.map(savedWorkout, WorkoutDTO.class);
    }

    public WorkoutDTO patchWorkout(Long id, JsonPatch patch) {
        Optional<Workout> currentWorkout = workoutRepository.findById(id);

        if (!(currentWorkout.isPresent() && currentWorkout.get().getIsActive()))
            throw new IllegalArgumentException("workoutID not found!");

        Workout workoutPatched = (Workout) JsonPatcherUtil.applyPatch(patch, currentWorkout.get());
        workoutRepository.save(workoutPatched);
        return modelMapper.map(workoutPatched, WorkoutDTO.class);
    }

    public DisableWorkoutDTO disableWorkout(Long id, JsonPatch patch) {
        Optional<Workout> currentWorkout = workoutRepository.findById(id);

        if (!(currentWorkout.isPresent() && currentWorkout.get().getIsActive()))
            throw new IllegalArgumentException("workoutID not found!");

        Workout workoutPatched = (Workout) JsonPatcherUtil.applyPatch(patch, currentWorkout.get());
        workoutRepository.save(workoutPatched);
        return modelMapper.map(workoutPatched, DisableWorkoutDTO.class);
    }

    public WorkoutDTO updateWorkout(Long id,Workout workout){
        Optional<Workout> optionalWorkout = workoutRepository.findById(id);

        if (optionalWorkout.isPresent() && optionalWorkout.get().getIsActive()) {
            Workout workoutToUpdate = optionalWorkout.get();
            workoutToUpdate.setName(workout.getName());
            workoutToUpdate.setCoachProfile(workout.getCoachProfile());
            workoutToUpdate.setCreator(workout.getCreator());
            workoutToUpdate.setDescription(workout.getDescription());
            workoutToUpdate.setTags(workout.getTags());
            workoutToUpdate.setLevel(workout.getLevel());
            workoutToUpdate.setIsPremium(workout.getIsPremium());
            workoutToUpdate.setImageUrl(workout.getImageUrl());
            workoutRepository.save(workoutToUpdate);

            return modelMapper.map(workoutToUpdate, WorkoutDTO.class);
        }
        return null;
    }
}

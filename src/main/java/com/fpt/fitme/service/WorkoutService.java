package com.fpt.fitme.service;

import com.fpt.fitme.dto.workout.DisableWorkoutDTO;
import com.fpt.fitme.dto.workout.WorkoutDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.workout.CoachProfile;
import com.fpt.fitme.entity.workout.Workout;
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
    private FitmeUserDetailsService fitmeUserDetailsService;

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

    public List<WorkoutDTO> getListWorkoutByTagID(long tagID) throws Exception {
        List<WorkoutDTO> result = new ArrayList<>();
        Optional<Tag> tag = tagRepository.findById(tagID);
        if (!(tag.isPresent() && tag.get().getIsActive())) throw new Exception("tagID not found!");
        workoutRepository.getWorkoutsByTags(tag.get()).forEach(workout -> {
            if (workout.getIsActive()) {
                WorkoutDTO dto = modelMapper.map(workout, WorkoutDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public List<WorkoutDTO> getListWorkoutByCoachID(long coachID) throws Exception {
        List<WorkoutDTO> result = new ArrayList<>();
        Optional<CoachProfile> coachProfile = coachProfileRepository.findById(coachID);
        if (!(coachProfile.isPresent() && coachProfile.get().getIsActive())) throw new Exception("coachID not found!");
        workoutRepository.getWorkoutsByCoachProfile(coachProfile.get()).forEach(workout -> {
            if (workout.getIsActive()) {
                WorkoutDTO dto = modelMapper.map(workout, WorkoutDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public WorkoutDTO createWorkout(Workout workout) throws Exception {
        AppUser appUser = fitmeUserDetailsService.getUserByAuthorization();
        Optional<CoachProfile> coachProfile = coachProfileRepository.findById(workout.getCoachProfile().getCoachID());

        if (!(coachProfile.isPresent() && coachProfile.get().getIsActive())) throw new Exception("coachID not found!");

        workout.setCoachProfile(coachProfile.get());
        workout.setCreator(appUser);
        workout.setEstimatedCalories(0);
        workout.setEstimatedDuration(0);
        workout.setIsActive(true);

        Workout savedWorkout = workoutRepository.save(workout);
        return modelMapper.map(savedWorkout, WorkoutDTO.class);
    }

    public WorkoutDTO patchWorkout(Long id, JsonPatch patch) throws Exception {
        Optional<Workout> currentWorkout = workoutRepository.findById(id);

        if (!(currentWorkout.isPresent() && currentWorkout.get().getIsActive()))
            throw new Exception("workoutID not found!");

        Workout workoutPatched = (Workout) JsonPatcherUtil.applyPatch(patch, currentWorkout.get());
        workoutRepository.save(workoutPatched);
        return modelMapper.map(workoutPatched, WorkoutDTO.class);
    }

    public DisableWorkoutDTO disableWorkout(Long id, JsonPatch patch) throws Exception {
        Optional<Workout> currentWorkout = workoutRepository.findById(id);

        if (!(currentWorkout.isPresent() && currentWorkout.get().getIsActive()))
            throw new Exception("workoutID not found!");

        Workout workoutPatched = (Workout) JsonPatcherUtil.applyPatch(patch, currentWorkout.get());
        workoutRepository.save(workoutPatched);
        return modelMapper.map(workoutPatched, DisableWorkoutDTO.class);
    }

    public WorkoutDTO updateWorkout(Long id, Workout workout) throws Exception {
        Optional<Workout> optionalWorkout = workoutRepository.findById(id);
        Optional<CoachProfile> coachProfile = coachProfileRepository.findById(workout.getCoachProfile().getCoachID());

        if (!(coachProfile.isPresent() && coachProfile.get().getIsActive())) throw new Exception("coachID not found!");

        if (optionalWorkout.isPresent() && optionalWorkout.get().getIsActive()) {
            Workout workoutToUpdate = optionalWorkout.get();
            workoutToUpdate.setName(workout.getName());
            workoutToUpdate.setCoachProfile(coachProfile.get());
            workoutToUpdate.setDescription(workout.getDescription());
            workoutToUpdate.setLevel(workout.getLevel());
            workoutToUpdate.setIsPremium(workout.getIsPremium());
            workoutToUpdate.setImageUrl(workout.getImageUrl());
            workoutRepository.save(workoutToUpdate);

            return modelMapper.map(workoutToUpdate, WorkoutDTO.class);
        }
        return null;
    }
}

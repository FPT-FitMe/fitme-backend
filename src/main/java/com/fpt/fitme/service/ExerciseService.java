package com.fpt.fitme.service;

import com.fpt.fitme.dto.Exercise.DisableExerciseDTO;
import com.fpt.fitme.dto.Exercise.ExerciseDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.tag.Tag;
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
public class ExerciseService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WorkoutExerciseService workoutExerciseService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    public List<ExerciseDTO> getListExercise() {
        List<ExerciseDTO> result = new ArrayList<>();
        exerciseRepository.findAll().forEach(exercise -> {
            if (exercise.getIsActive()) {
                ExerciseDTO dto = modelMapper.map(exercise, ExerciseDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public ExerciseDTO getExerciseByID(long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isPresent() && exerciseOptional.get().getIsActive())
            return modelMapper.map(exerciseOptional.get(), ExerciseDTO.class);
        return null;
    }

    public ExerciseDTO createExercise(Exercise exercise) throws Exception {
        Optional<AppUser> creator = appUserRepository.findById(exercise.getCreator().getUserID());

        if (!creator.isPresent()) throw new Exception("creatorID not found!");

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
        return modelMapper.map(savedExercise, ExerciseDTO.class);
    }

    public ExerciseDTO createExercise(Exercise exercise, long workoutID) throws Exception {
        Optional<AppUser> creator = appUserRepository.findById(exercise.getCreator().getUserID());
        if (!creator.isPresent()) throw new Exception("creatorID not found!");

        Optional<Workout> workout = workoutRepository.findById(workoutID);
        if (!(workout.isPresent() && workout.get().getIsActive()))
            throw new Exception("workoutID not found!");

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
            return modelMapper.map(savedExercise, ExerciseDTO.class);
        }
        return null;
    }

    public ExerciseDTO patchExercise(Long id, JsonPatch patch) throws Exception {
        Optional<Exercise> currentExercise = exerciseRepository.findById(id);

        if (!(currentExercise.isPresent() && currentExercise.get().getIsActive()))
            throw new Exception("exerciseID not found!");

        Exercise exercisePatched = (Exercise) JsonPatcherUtil.applyPatch(patch, currentExercise.get());
        exerciseRepository.save(exercisePatched);
        return modelMapper.map(exercisePatched, ExerciseDTO.class);
    }

    public DisableExerciseDTO disableExercise(Long id, JsonPatch patch) throws Exception {
        Optional<Exercise> currentExercise = exerciseRepository.findById(id);

        if (!(currentExercise.isPresent() && currentExercise.get().getIsActive()))
            throw new Exception("exerciseID not found!");

        Exercise exercisePatched = (Exercise) JsonPatcherUtil.applyPatch(patch, currentExercise.get());
        exerciseRepository.save(exercisePatched);

        //neu thang exercise co trong workout nao thi xoa thang do di
        List<Workout_Exercise> list = workoutExerciseRepository.getWorkout_ExerciseByExerciseID(currentExercise.get());

        if (!list.isEmpty())
            workoutExerciseService.deleteAllByExerciseID(id);

        return modelMapper.map(exercisePatched, DisableExerciseDTO.class);
    }

    public ExerciseDTO updateExercise(Long id, Exercise exercise) throws Exception{
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);

        if (optionalExercise.isPresent() && optionalExercise.get().getIsActive()) {
            Exercise exerciseToUpdate = optionalExercise.get();
            exerciseToUpdate.setName(exercise.getName());
            exerciseToUpdate.setDescription(exercise.getDescription());
            exerciseToUpdate.setVideoUrl(exercise.getVideoUrl());
            exerciseToUpdate.setTags(exercise.getTags());
            exerciseToUpdate.setBaseDuration(exercise.getBaseDuration());
            exerciseToUpdate.setBaseRepPerRound(exercise.getBaseRepPerRound());
            exerciseToUpdate.setBaseKcal(exercise.getBaseKcal());
            exerciseToUpdate.setImageUrl(exercise.getImageUrl());
            exerciseRepository.save(exerciseToUpdate);

            //neu exercise co trong workout nao thi update workout do
            List<Workout_Exercise> listUpdate=workoutExerciseRepository.getWorkout_ExerciseByExerciseID(exercise);
            if(!listUpdate.isEmpty()){
                for (Workout_Exercise we:listUpdate) {
                    workoutExerciseService.updateAllByWorkoutID(we.getWorkoutID().getWorkoutID());
                }
            }

            return modelMapper.map(exerciseToUpdate, ExerciseDTO.class);
        }
        return null;
    }
}
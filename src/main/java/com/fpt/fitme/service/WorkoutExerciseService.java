package com.fpt.fitme.service;

import com.fpt.fitme.dto.workoutexercise.WorkoutExerciseDTO;
import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.entity.workout.WorkoutExercise;
import com.fpt.fitme.repository.ExerciseRepository;
import com.fpt.fitme.repository.WorkoutExerciseRepository;
import com.fpt.fitme.repository.WorkoutRepository;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutExerciseService {

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ModelMapper modelMapper;

    //return list Exercise trong workoutId da dua
    public List<WorkoutExerciseDTO> addListExerciseToWorkout(long workoutID, Exercise[] exercises) throws Exception {
        Optional<Workout> workoutOptional = workoutRepository.findById(workoutID);
        if (!(workoutOptional.isPresent() && workoutOptional.get().getIsActive()))
            throw new Exception("workoutID not found!");

        List<WorkoutExercise> listAdd = new ArrayList<>();
        long exerciseOrder = 0;
        if (workoutExerciseRepository.findMaxExerciseOrder(workoutOptional.get()) != null) {
            exerciseOrder = workoutExerciseRepository.findMaxExerciseOrder(workoutOptional.get());
        }

        for (Exercise exercise : exercises) {
            Optional<Exercise> exerciseOptional = exerciseRepository.findById(exercise.getExerciseID());
            if (!(exerciseOptional.isPresent() && exerciseOptional.get().getIsActive()))
                throw new Exception("exerciseID not found!");
            exerciseOrder += 1;
            WorkoutExercise workout_exercise = new WorkoutExercise();
            workout_exercise.setWorkoutID(workoutOptional.get());
            workout_exercise.setExerciseID(exerciseOptional.get());
            workout_exercise.setExerciseOrder(exerciseOrder);
            listAdd.add(workout_exercise);
        }
        if (!listAdd.isEmpty()) {
            workoutExerciseRepository.saveAll(listAdd);
            updateAllByWorkoutID(workoutOptional.get().getWorkoutID());

            return getListExerciseByOrder(workoutID);
        }
        return null;
    }

    //remove 1 exercise out of workout
    public boolean deleteExerciseWorkout(long exerciseID, long workoutID, long exerciseOrder) throws Exception {
        Optional<Workout> workoutOptional = workoutRepository.findById(workoutID);
        if (!(workoutOptional.isPresent() && workoutOptional.get().getIsActive()))
            throw new Exception("workoutID not found!");

        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseID);
        if (!(exerciseOptional.isPresent() && exerciseOptional.get().getIsActive()))
            throw new Exception("exerciseID not found!");

        List<WorkoutExercise> list = workoutExerciseRepository.getWorkout_ExerciseByExerciseIDAndWorkoutIDAndExerciseOrder(exerciseOptional.get(), workoutOptional.get(), exerciseOrder);

        if (list.isEmpty())
            throw new Exception("Exercise " + exerciseOptional.get().getExerciseID() + "is not in this Workout " + workoutOptional.get().getWorkoutID());

        workoutExerciseRepository.deleteById(list.get(0).getWorkoutExerciseID());

        //remove xong thi update lai workout baseDuration/baseKcal
        int totalDuration = workoutOptional.get().getEstimatedDuration() - exerciseOptional.get().getBaseDuration() - 15;
        int totalKcal = workoutOptional.get().getEstimatedCalories() - exerciseOptional.get().getBaseKcal();
        workoutRepository.updateDurationCaloriesWorkout(workoutID, totalKcal, totalDuration);

        return true;
    }

    public List<WorkoutExerciseDTO> getListExerciseByOrder(long workoutID) throws Exception {

        Optional<Workout> workoutOptional = workoutRepository.findById(workoutID);
        if (!(workoutOptional.isPresent() && workoutOptional.get().getIsActive()))
            throw new Exception("workoutID not found!");

        modelMapper.getConfiguration()
                .setPropertyCondition(context ->
                        !(context.getSource() instanceof PersistentCollection)
                );

        List<WorkoutExercise> result = workoutExerciseRepository.findByWorkoutIDOrderByExerciseOrderAsc(workoutOptional.get());
        return result.stream().map(workout_exercise -> modelMapper.map(workout_exercise, WorkoutExerciseDTO.class)).collect(Collectors.toList());
    }

    public void deleteAllByExerciseID(long exerciseID) throws Exception {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseID);
        if (!(exerciseOptional.isPresent()))
            throw new Exception("exerciseID not found!");

        List<WorkoutExercise> list = workoutExerciseRepository.getWorkout_ExerciseByExerciseID(exerciseOptional.get());

        if (list.isEmpty())
            throw new Exception("Exercise " + exerciseOptional.get().getExerciseID() + "is not in any Workout ");

        workoutExerciseRepository.deleteAll(list);

        //remove xong thi update lai workout baseDuration/baseKcal
        //update tung thang workout
        for (WorkoutExercise we : list) {
           updateAllByWorkoutID(we.getWorkoutID().getWorkoutID());
        }
    }


    public void updateAllByWorkoutID(long workoutID) throws Exception {
        Optional<Workout> workoutOptional = workoutRepository.findById(workoutID);
        if (!(workoutOptional.isPresent() && workoutOptional.get().getIsActive()))
            throw new Exception("workoutID not found!");

        List<WorkoutExercise> list = workoutExerciseRepository.getWorkout_ExerciseByWorkoutID(workoutOptional.get());
        if (list.isEmpty())
            throw new Exception("Workout does not have any Exercise!");
        int totalDuration = -15;
        int totalKcal = 0;
        for (WorkoutExercise we : list) {
            totalDuration+=we.getExerciseID().getBaseDuration()+15;
            totalKcal+=we.getExerciseID().getBaseKcal();
        }
        workoutRepository.updateDurationCaloriesWorkout(workoutID, totalKcal, totalDuration);
    }

    public void deleteAllByWorkoutID(long workoutID) throws Exception {
        Optional<Workout> workoutOptional = workoutRepository.findById(workoutID);
        if (!(workoutOptional.isPresent() && workoutOptional.get().getIsActive()))
            throw new Exception("workoutID not found!");

        List<WorkoutExercise> list = workoutExerciseRepository.getWorkout_ExerciseByWorkoutID(workoutOptional.get());
        if (list.isEmpty())
            throw new Exception("Workout does not have any Exercise!");

        workoutExerciseRepository.deleteAll(list);
    }

    public List<WorkoutExerciseDTO> updateExerciseListByOrder(long workoutID, Exercise[] exercises) throws Exception{
        //xoa het listEx Workout
        deleteAllByWorkoutID(workoutID);
        //add lai listEx moi theo order moi
        //return lai list Ex
        return addListExerciseToWorkout(workoutID,exercises);
    }
}

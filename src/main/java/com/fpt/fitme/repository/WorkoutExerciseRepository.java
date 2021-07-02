package com.fpt.fitme.repository;

import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.entity.workout.WorkoutExercise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutExerciseRepository extends CrudRepository<WorkoutExercise, Long> {

    long countWorkout_ExerciseByWorkoutID(Workout workout);

    @Query("SELECT MAX(we.exerciseOrder) FROM WorkoutExercise  as we WHERE we.workoutID=:workout")
    Integer findMaxExerciseOrder(@Param("workout") Workout workout);

    List<WorkoutExercise> findByWorkoutIDOrderByExerciseOrderAsc(Workout workout);

    List<WorkoutExercise> getWorkout_ExerciseByExerciseIDAndWorkoutIDAndExerciseOrder(Exercise exerciseID, Workout workoutID, long exerciseOrder);

    List<WorkoutExercise> getWorkout_ExerciseByExerciseID(Exercise exerciseID);

    List<WorkoutExercise> getWorkout_ExerciseByWorkoutID(Workout workoutID);
}

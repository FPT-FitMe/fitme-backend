package com.fpt.fitme.repository;

import com.fpt.fitme.entity.exercise.Exercise;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.entity.workout.Workout_Exercise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutExerciseRepository extends CrudRepository<Workout_Exercise, Long> {

    long countWorkout_ExerciseByWorkoutID(Workout workout);

    @Query("SELECT MAX(we.exerciseOrder) FROM Workout_Exercise  as we WHERE we.workoutID=:workout")
    Integer findMaxExerciseOrder(@Param("workout") Workout workout);

    List<Workout_Exercise> findByWorkoutIDOrderByExerciseOrderAsc(Workout workout);

    List<Workout_Exercise> getWorkout_ExerciseByExerciseIDAndWorkoutIDAndExerciseOrder( Exercise exerciseID,Workout workoutID,long exerciseOrder);

}

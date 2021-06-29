package com.fpt.fitme.repository;

import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.entity.workout.Workout_Exercise;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutExerciseRepository extends CrudRepository<Workout_Exercise, Long> {

    long countWorkout_ExerciseByWorkoutID(Workout workout);
}

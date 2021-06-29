package com.fpt.fitme.repository;

import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.entity.workout.WorkoutExercise;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutExerciseRepository extends CrudRepository<WorkoutExercise, Long> {

    long countWorkoutExerciseByWorkout(Workout workout);
}

package com.fpt.fitme.repository;

import com.fpt.fitme.entity.workout.Workout;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {
}

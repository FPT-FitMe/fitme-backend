package com.fpt.fitme.repository;

import com.fpt.fitme.entity.exercise.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}

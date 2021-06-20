package com.fpt.fitme.repository;

import com.fpt.fitme.entity.meal.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {
}

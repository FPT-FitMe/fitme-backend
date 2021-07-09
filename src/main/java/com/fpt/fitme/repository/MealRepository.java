package com.fpt.fitme.repository;

import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.workout.CoachProfile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface MealRepository extends CrudRepository<Meal, Long> {

    List<Meal> findMealsByTagsIn(Set<Tag> tags);

    List<Meal> findMealsByTags(Tag tag);

    List<Meal> getMealsByCoachProfile(CoachProfile coachProfile);
}

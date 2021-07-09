package com.fpt.fitme.dto.favorite;

import com.fpt.fitme.dto.meal.MealDTO;
import com.fpt.fitme.dto.workout.WorkoutDTO;
import lombok.Data;

import java.util.Set;

@Data
public class AllFavoriteDTO {
    Set<WorkoutDTO> traineeFavoriteWorkouts;
    Set<MealDTO> traineeFavoriteMeals;
}

package com.fpt.fitme.dto.favorite;

import com.fpt.fitme.entity.workout.Workout;
import lombok.Data;

import java.util.Set;

@Data
public class WorkoutFavoriteDTO {
    Set<Workout> traineeFavoriteWorkouts;
}

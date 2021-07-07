package com.fpt.fitme.dto.workout;

import lombok.Data;

@Data
public class WorkoutDTOLazy {
    private Long workoutID;
    private String name;
    private Integer estimatedDuration;
    private Integer estimatedCalories;
    private Integer level;
    private Boolean isPremium;
    private String imageUrl;
}

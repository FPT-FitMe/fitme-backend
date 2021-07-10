package com.fpt.fitme.dto.workout;

import com.fpt.fitme.dto.tag.TagDTO;
import com.fpt.fitme.dto.workoutexercise.WorkoutExerciseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class WorkoutDTOLazy {
    private Long workoutID;
    private String name;
    private String description;
    private Integer estimatedDuration;
    private Integer estimatedCalories;
    private Integer level;
    private Boolean isPremium;
    private String imageUrl;
    private Set<TagDTO> tags;
    private Set<WorkoutExerciseDTO> workoutExercises;
}

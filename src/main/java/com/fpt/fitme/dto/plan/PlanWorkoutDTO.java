package com.fpt.fitme.dto.plan;

import com.fpt.fitme.dto.workout.WorkoutDTOLazy;
import lombok.Data;

@Data
public class PlanWorkoutDTO {
    private Long id;
    private WorkoutDTOLazy workout;
    private String status;
}

package com.fpt.fitme.dto.plan;

import com.fpt.fitme.dto.target.TargetDTO;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class PlanDTO {
    private Long planID;
    private TargetDTO target;
    private Set<PlanMealDTO> planMeals;
    private Set<PlanWorkoutDTO> planWorkouts;
    private Date date;
}

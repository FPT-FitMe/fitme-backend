package com.fpt.fitme.dto.plan;

import com.fpt.fitme.dto.meal.MealDTOLazy;
import lombok.Data;

@Data
public class PlanMealDTO {
    private Long id;
    private MealDTOLazy meal;
    private String status;
}

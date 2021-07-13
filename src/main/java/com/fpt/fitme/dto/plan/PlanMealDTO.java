package com.fpt.fitme.dto.plan;

import com.fpt.fitme.dto.meal.MealDTOLazy;
import lombok.Data;

@Data
public class PlanMealDTO implements Comparable<PlanMealDTO>{
    private Long id;
    private MealDTOLazy meal;
    private String status;

    @Override
    public int compareTo(PlanMealDTO o) {
        return this.id.compareTo(o.id);
    }
}

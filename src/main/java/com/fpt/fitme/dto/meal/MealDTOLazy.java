package com.fpt.fitme.dto.meal;

import lombok.Data;

@Data
public class MealDTOLazy {
    private Long mealID;
    private String name;
    private Integer cookingTime;
    private Boolean isPremium;
    private String imageUrl;
    private Float calories;
    private Float carbAmount;
    private Float fatAmount;

}

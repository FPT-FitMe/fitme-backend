package com.fpt.fitme.dto.meal;

import com.fpt.fitme.dto.coachprofile.CoachProfileDTO;
import com.fpt.fitme.dto.tag.TagDTO;
import lombok.Data;

import java.util.Set;

@Data
public class MealDTOLazy {
    private Long mealID;
    private String name;
    private String description;
    private Set<TagDTO> tags;
    private Integer cookingTime;
    private Boolean isPremium;
    private String imageUrl;
    private Float calories;
    private Float carbAmount;
    private Float fatAmount;

}

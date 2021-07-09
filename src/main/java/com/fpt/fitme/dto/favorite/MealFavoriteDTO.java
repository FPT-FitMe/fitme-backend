package com.fpt.fitme.dto.favorite;

import com.fpt.fitme.dto.log.MealLogDTO;
import lombok.Data;

import java.util.Set;

@Data
public class MealFavoriteDTO {
    Set<MealLogDTO> traineeFavoriteMeals;
}

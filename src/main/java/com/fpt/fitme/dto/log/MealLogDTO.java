package com.fpt.fitme.dto.log;

import com.fpt.fitme.dto.appUser.AppUserDTO;
import com.fpt.fitme.dto.appUser.AppUserDTOLazy;
import com.fpt.fitme.dto.meal.MealDTO;
import lombok.Data;

import java.util.Date;

@Data
public class MealLogDTO {

    private Long mealLogID;
    private AppUserDTOLazy trainee;
    private MealDTO meal;
    private Date createdAt;
}

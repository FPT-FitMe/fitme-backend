package com.fpt.fitme.dto.appUser;

import com.fpt.fitme.dto.meal.MealDTO;
import com.fpt.fitme.dto.workout.WorkoutDTO;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class AppUserDTO {
    private Long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Integer age;
    private Integer gender;
    private RoleDTO role;
    private Set<WorkoutDTO> traineeFavoriteWorkouts;
    private Set<MealDTO> traineeFavoriteMeals;
    private Float height;
    private String profileImageUrl;
    private Boolean isPremium;
    private Date createdDate;
    private Date lastModifiedDate;
}

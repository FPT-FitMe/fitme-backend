package com.fpt.fitme.entity.log;

import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.entity.appuser.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meal_log")
@Data
public class MealLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_log_id")
    private Long mealLogID;

    @ManyToOne
    private AppUser trainee;

    @ManyToOne
    private Meal meal;

    @Column(name = "createdAt")
    private Date createdAt;
}

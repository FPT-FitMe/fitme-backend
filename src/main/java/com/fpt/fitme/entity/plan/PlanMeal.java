package com.fpt.fitme.entity.plan;

import com.fpt.fitme.entity.meal.Meal;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "plan_meal")
@Data
public class PlanMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plan_meal_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(optional = false)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @Column(name = "has_skipped")
    private Boolean hasSkipped;

    @Column(name = "is_finished")
    private Boolean isFinished;
}

package com.fpt.fitme.entity.plan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fpt.fitme.entity.meal.Meal;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "plan_meal")
@Getter
@Setter
@RequiredArgsConstructor
@ToString(exclude = {"plan"})
public class PlanMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plan_meal_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    @JsonBackReference
    private Plan plan;

    @ManyToOne(optional = false)
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @Column(name = "status")
    private String status;
}

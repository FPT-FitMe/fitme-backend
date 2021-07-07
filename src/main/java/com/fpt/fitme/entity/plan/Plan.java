package com.fpt.fitme.entity.plan;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fpt.fitme.entity.target.Target;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "plan")
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plan_id")
    private Long planID;

    @ManyToOne
    private Target target;

    @OneToMany(mappedBy = "plan", orphanRemoval = true)
    @JsonManagedReference
    private Set<PlanMeal> planMeals;

    @OneToMany(mappedBy = "plan", orphanRemoval = true)
    @JsonManagedReference
    private Set<PlanWorkout> planWorkouts;

    @Column(name = "date")
    private Date date;

}

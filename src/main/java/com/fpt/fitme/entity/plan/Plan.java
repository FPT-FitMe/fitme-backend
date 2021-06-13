package com.fpt.fitme.models.plan;

import com.fpt.fitme.models.meal.Meal;
import com.fpt.fitme.models.tag.Tag;
import com.fpt.fitme.models.target.Target;
import com.fpt.fitme.models.workout.Workout;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plan")
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planID;

    @ManyToOne
    private Target target;

    @Column(name = "date")
    private Date date;

    @ManyToMany
    @JoinTable(
            name = "workout_plan",
            joinColumns = { @JoinColumn(name = "plan_id")},
            inverseJoinColumns = { @JoinColumn(name = "workout_id")}
    )
    private Set<Workout> workouts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "meal_plan",
            joinColumns = { @JoinColumn(name = "plan_id")},
            inverseJoinColumns = { @JoinColumn(name = "meal_id")}
    )
    private Set<Meal> meals = new HashSet<>();
}

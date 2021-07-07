package com.fpt.fitme.entity.plan;

import com.fpt.fitme.entity.workout.Workout;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "plan_workout")
@Data
public class PlanWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "plan_workout")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @Column(name = "has_skipped", nullable = false)
    private Boolean hasSkipped = false;

    @Column(name = "is_finished", nullable = false)
    private Boolean isFinished = false;


}

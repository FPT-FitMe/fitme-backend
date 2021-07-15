package com.fpt.fitme.entity.plan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fpt.fitme.entity.workout.Workout;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "plan_workout")
@Getter
@Setter
@RequiredArgsConstructor
@ToString(exclude = {"plan"})
public class PlanWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_workout")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    @JsonBackReference
    private Plan plan;

    @ManyToOne(optional = false)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @Column(name = "status")
    private String status;

}

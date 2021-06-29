package com.fpt.fitme.entity.workout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpt.fitme.entity.exercise.Exercise;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "workout_exercise")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "workout_exercise_id")
    private Long workoutExerciseID;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonIgnoreProperties("workout_exercises")
    private Exercise exercise;

    @Column
    private Long exerciseOrder;
}

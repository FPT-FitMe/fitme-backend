package com.fpt.fitme.entity.workout;

import com.fasterxml.jackson.annotation.*;
import com.fpt.fitme.entity.exercise.Exercise;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "workoutExercise")
public class WorkoutExercise {

    //workout can have duplicate exercise
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_exercise_id")
    private long workoutExerciseID;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    @JsonIgnoreProperties("workout_exercises")
    private Workout workoutID;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonIgnoreProperties("workout_exercises")
    private Exercise exerciseID;

    @Column
    private long exerciseOrder;

}

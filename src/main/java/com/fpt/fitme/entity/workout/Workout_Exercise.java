package com.fpt.fitme.entity.workout;

import com.fpt.fitme.entity.exercise.Exercise;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "workout_exercise")
public class Workout_Exercise {

    //workout can have duplicate exercise
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exerciseOrder")
    private int exerciseOrder;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workoutID;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exerciseID;



}

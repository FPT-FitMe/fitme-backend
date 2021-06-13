package com.fpt.fitme.entity.exercise;

import com.fpt.fitme.entity.workout.Workout;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long exerciseID;

    @ManyToOne
    private Workout workout;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "videoFile")
    private String videoFile;

    @Column(name = "baseDuration")
    private int baseDuration;

    @Column(name = "baseRepPerRound")
    private int baseRepPerRound;

    @Column(name = "imageFile")
    private String imageFile;
}

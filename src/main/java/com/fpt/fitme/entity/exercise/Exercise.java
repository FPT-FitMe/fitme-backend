package com.fpt.fitme.entity.exercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpt.fitme.entity.workout.Workout;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exercise_id")
    private Long exerciseID;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Workout workout;

    @Column(name = "exercise_order")
    private Integer exerciseOrder;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "videoFile")
    private String videoFile;

    @Column(name = "baseDuration")
    private Integer baseDuration;

    @Column(name = "baseRepPerRound")
    private Integer baseRepPerRound;

    @Column(name = "imageFile")
    private String imageFile;
}

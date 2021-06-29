package com.fpt.fitme.entity.exercise;

import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.workout.WorkoutExercise;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exercise")
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exercise_id")
    private Long exerciseID;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "exercise_id")
    private Set<WorkoutExercise> workoutExercises;

    @ManyToMany
    @JoinTable(
            name = "exercise_tag",
            joinColumns = { @JoinColumn(name = "exercise_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "exercise_order")
    private Integer exerciseOrder;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "baseDuration")
    private Integer baseDuration;

    @Column(name = "baseRepPerRound")
    private Integer baseRepPerRound;

    @Column(name = "base_calories_per_round")
    private Integer baseCaloriesPerRound;

    @Column(name = "image_url")
    private String imageUrl;

    public Set<WorkoutExercise> getWorkoutExercises() {
        return workoutExercises;
    }

    public void setWorkoutExercises(Set<WorkoutExercise> workoutExercises) {
        this.workoutExercises = workoutExercises;
    }
}

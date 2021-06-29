package com.fpt.fitme.entity.workout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.plan.PlanWorkout;
import com.fpt.fitme.entity.tag.Tag;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workout")
@Data
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "workout_id")
    private Long workoutId;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CoachProfile coachProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AppUser creator;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "workout_tag",
            joinColumns = { @JoinColumn(name = "workout_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "workout", orphanRemoval = true)
    private Set<WorkoutExercise> workoutExercises;

    @OneToMany(mappedBy = "workout", orphanRemoval = true)
    private Set<PlanWorkout> planWorkouts;

    @Column(name = "level")
    private Integer level;

    @Column(name = "isPremium", nullable = false)
    private Boolean isPremium;

    @Column(name = "image_url", length = 150)
    private String imageUrl;

}

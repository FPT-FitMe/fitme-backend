package com.fpt.fitme.entity.workout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.appuser.AppUser;
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
    private Long workoutID;

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

    @Column(name = "estimated_duration")
    private Integer estimatedDuration;

    @Column(name = "estimated_calories")
    private Float estimatedCalories;

    @Column(name = "level")
    private Integer level;

    @Column(name = "isPremium", nullable = false)
    private Boolean isPremium;

    @Column(name = "imageFile", length = 150)
    private String imageFile;
}

package com.fpt.fitme.models.workout;

import com.fpt.fitme.models.tag.Tag;
import com.fpt.fitme.models.appuser.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workout")
@Data
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Long workoutID;

    @ManyToOne
    private CoachProfile coachProfile;

    @ManyToOne
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
    private int estimatedDuration;

    @Column(name = "estimated_calories")
    private float estimatedCalories;

    @Column(name = "level")
    private int level;

    @Column(name = "isPremium", nullable = false)
    private boolean isPremium;

    @Column(name = "imageFile", length = 150)
    private String imageFile;
}

package com.fpt.fitme.entity.workout;

import com.fasterxml.jackson.annotation.*;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.appuser.AppUser;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workout")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "workoutID")
@EntityListeners(AuditingEntityListener.class)
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Long workoutID;

    @Column(name = "workout_name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CoachProfile coachProfile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","traineeFavoriteWorkouts"})
    private AppUser creator;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "workout_tag",
            joinColumns = { @JoinColumn(name = "workout_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "estimated_duration")
    private Integer estimatedDuration;

    @Column(name = "estimated_calories")
    private Integer estimatedCalories;

    @Column(name = "level")
    private Integer level;

    @Column(name = "is_premium", nullable = false)
    private Boolean isPremium;

    @Column(name = "image_url", length = 150)
    private String imageUrl;

    @OneToMany(mappedBy = "workoutID")
    private Set<WorkoutExercise> workoutExercises;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}
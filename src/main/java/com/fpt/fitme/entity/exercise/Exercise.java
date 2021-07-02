package com.fpt.fitme.entity.exercise;

import com.fasterxml.jackson.annotation.*;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.workout.WorkoutExercise;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "exercise")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exercise_id")
    private Long exerciseID;

    @OneToMany
    private List<WorkoutExercise> workout_exercises=new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "exercise_tag",
            joinColumns = { @JoinColumn(name = "exercise_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Tag> tags = new HashSet<>();

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

    @Column(name = "baseKcal")
    private Integer baseKcal;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","traineeFavoriteWorkouts"})
    private AppUser creator;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}
package com.fpt.fitme.entity.meal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.workout.CoachProfile;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meal")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long mealID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AppUser creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CoachProfile coachProfile;

    @ManyToMany
    @JoinTable(
            name = "meal_tag",
            joinColumns = { @JoinColumn(name = "meal_id")},
            inverseJoinColumns = { @JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Column(name = "is_premium")
    private Boolean isPremium;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "calories")
    private Float calories;

    @Column(name = "carb_amount")
    private Float carbAmount;

    @Column(name = "fat_amount")
    private Float fatAmount;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}

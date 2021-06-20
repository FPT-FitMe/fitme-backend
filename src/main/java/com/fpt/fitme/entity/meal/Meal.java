package com.fpt.fitme.entity.meal;

import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.appuser.AppUser;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meal")
@Data
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_id")
    private Long mealID;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser creator;

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

    @Column(name = "image_file")
    private String imageFile;

    @Column(name = "calories")
    private Float calories;

    @Column(name = "carb_amount")
    private Float carbAmount;

    @Column(name = "fat_amount")
    private Float fatAmount;
}

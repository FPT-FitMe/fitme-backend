package com.fpt.fitme.entity.appuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.entity.workout.Workout;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password", nullable = false, length = 64)
    @JsonIgnore
    private String password;

    @Column(name = "email", unique = true, length = 64)
    private String email;

    @Column(name = "phone", unique = true, length = 15)
    private String phone;

    @Column(name = "age")
    @Min(14)
    @Max(70)
    private Integer age;

    @Column(name = "gender")
    private Integer gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AppUserRole role;

    @ManyToMany
    @JoinTable(
            name = "trainee_favorite_workout",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "workout_id")}
    )
    private Set<Workout> traineeFavoriteWorkouts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "trainee_favorite_meal",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "meal_id")}
    )
    private Set<Meal> traineeFavoriteMeals = new HashSet<>();

    @Column(name = "height")
    @Min(65)
    @Max(300)
    private Float height;

    @Column(name = "diet_preference_type")
    private Long dietPreferenceType;

    @Column(name = "exercise_frequency_type")
    private Long exerciseFrequencyType;

    @Column(name = "workout_intensity")
    private Float workoutIntensity;

    @Column(name = "is_premium", nullable = false)
    private Boolean isPremium = false;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

}

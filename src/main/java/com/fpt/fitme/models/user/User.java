package com.fpt.fitme.models.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "User")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 30)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "email", unique = true, length = 64)
    private String email;

    @Column(name = "phone", unique = true, length = 15)
    private String phone;

    @Column(name = "age")
    @Min(14)
    @Max(70)
    private int age;

    @Column(name = "gender")
    private int gender;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserRole role;

    @Column(name = "height")
    @Min(65)
    @Max(300)
    private float height;

    @Column(name = "diet_preference_type")
    private int dietPreferenceType;

    @Column(name = "long_term_goal_type")
    private int longTermGoalType;

    @Column(name = "exercise_frequency_type")
    private int exercise_frequency_type;

    @Column(name = "workout_intensity")
    private float workoutIntensity;
}

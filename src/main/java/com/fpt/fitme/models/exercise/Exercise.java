package com.fpt.fitme.models.exercise;

import com.fpt.fitme.models.user.User;
import com.fpt.fitme.models.workout.CoachProfile;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
@Data
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long exerciseID;


}

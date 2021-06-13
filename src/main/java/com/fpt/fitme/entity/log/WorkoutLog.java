package com.fpt.fitme.models.log;

import com.fpt.fitme.models.appuser.AppUser;
import com.fpt.fitme.models.workout.Workout;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "workout_log")
@Data
public class WorkoutLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_log_id")
    private Long workoutLogID;

    @ManyToOne
    private AppUser trainee;

    @ManyToOne
    private Workout workout;

    @Column(name = "duration")
    private int duration;

    @Column(name = "totalCalories")
    private int totalCalories;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "difficult_feedback")
    private int difficultFeedback;

    @Column(name = "experience_feedback")
    private int experienceFeedback;
}

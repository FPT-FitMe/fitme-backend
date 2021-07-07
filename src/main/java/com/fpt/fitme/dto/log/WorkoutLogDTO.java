package com.fpt.fitme.dto.log;

import com.fpt.fitme.dto.appUser.AppUserDTO;
import com.fpt.fitme.dto.workout.WorkoutDTO;
import lombok.Data;

import java.util.Date;

@Data
public class WorkoutLogDTO {

    private Long workoutLogID;
    private AppUserDTO trainee;
    private WorkoutDTO workout;
    private Integer duration;
    private Integer totalCalories;
    private Date createdAt;
    private Integer difficultFeedback;
    private Integer experienceFeedback;
}

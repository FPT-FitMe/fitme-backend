package com.fpt.fitme.dto.log;

import com.fpt.fitme.dto.appUser.AppUserDTOLazy;
import com.fpt.fitme.dto.workout.WorkoutDTOLazy;
import lombok.Data;

import java.util.Date;

@Data
public class WorkoutLogDTO {

    private Long workoutLogID;
    private AppUserDTOLazy trainee;
    private WorkoutDTOLazy workout;
    private Integer duration;
    private Integer totalCalories;
    private Date createdAt;
    private Integer difficultFeedback;
    private Integer experienceFeedback;
}

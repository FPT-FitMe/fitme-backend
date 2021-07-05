package com.fpt.fitme.dto.workout;

public class DisableWorkoutDTO {
    private Long workoutID;
    private Boolean isActive;

    public Long getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(Long workoutID) {
        this.workoutID = workoutID;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

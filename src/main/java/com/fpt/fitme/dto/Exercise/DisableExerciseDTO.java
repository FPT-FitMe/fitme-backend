package com.fpt.fitme.dto.Exercise;

public class DisableExerciseDTO {
    private Long exerciseID;
    private Boolean isActive;

    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

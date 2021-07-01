package com.fpt.fitme.dto;

import com.fpt.fitme.dto.Exercise.ExerciseDTO;

public class WorkoutExerciseDTO {
    private ExerciseDTO exerciseID;
    private long exerciseOrder;

    public ExerciseDTO getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(ExerciseDTO exerciseID) {
        this.exerciseID = exerciseID;
    }

    public long getExerciseOrder() {
        return exerciseOrder;
    }

    public void setExerciseOrder(long exerciseOrder) {
        this.exerciseOrder = exerciseOrder;
    }
}

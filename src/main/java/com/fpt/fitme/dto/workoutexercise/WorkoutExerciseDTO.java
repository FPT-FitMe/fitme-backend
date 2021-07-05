package com.fpt.fitme.dto.workoutexercise;

import com.fpt.fitme.dto.exercise.ExerciseDTO;

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

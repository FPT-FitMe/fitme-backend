package com.fpt.fitme.dto.log;

import lombok.Data;

import java.util.Set;

@Data
public class AllLogsDTO {
    Set<MealLogDTO> mealLogs;
    Set<WorkoutLogDTO> workoutLogs;
    Set<WeightLogDTO> weightLogs;
}

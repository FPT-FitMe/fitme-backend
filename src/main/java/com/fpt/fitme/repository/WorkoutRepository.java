package com.fpt.fitme.repository;

import com.fpt.fitme.entity.workout.Workout;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Workout w SET w.estimatedCalories=:kcalTotal,w.estimatedDuration=:durationTotal WHERE w.workoutID=:workoutID")
    int updateDurationCaloriesWorkout(@Param("workoutID") long workoutID,@Param("kcalTotal") int kcalTotal,@Param("durationTotal") int durationTotal);
}

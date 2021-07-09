package com.fpt.fitme.repository;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.log.WorkoutLog;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Set;

public interface WorkoutLogRepository extends CrudRepository<WorkoutLog, Long> {

    public Set<WorkoutLog> findAllByCreatedAtBetweenAndTrainee(Date from, Date to, AppUser trainee);
}

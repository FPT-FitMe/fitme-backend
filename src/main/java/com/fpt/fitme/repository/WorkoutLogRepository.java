package com.fpt.fitme.repository;

import com.fpt.fitme.entity.log.WorkoutLog;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutLogRepository extends CrudRepository<WorkoutLog, Long> {
}

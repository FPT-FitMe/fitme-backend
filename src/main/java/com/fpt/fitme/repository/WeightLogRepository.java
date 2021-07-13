package com.fpt.fitme.repository;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.log.WeightLog;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Set;

public interface WeightLogRepository extends CrudRepository<WeightLog, Long> {

    public Set<WeightLog> findAllByCreatedAtBetweenAndTrainee(Date from, Date to, AppUser trainee);

    public Set<WeightLog> findAllByTrainee(AppUser trainee);
}

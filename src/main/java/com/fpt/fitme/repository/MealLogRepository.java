package com.fpt.fitme.repository;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.log.MealLog;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Set;

public interface MealLogRepository extends CrudRepository<MealLog, Long> {

    public Set<MealLog> findAllByCreatedAtBetweenAndTrainee(Date from, Date to, AppUser trainee);
}

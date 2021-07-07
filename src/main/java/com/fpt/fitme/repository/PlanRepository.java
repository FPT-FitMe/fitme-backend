package com.fpt.fitme.repository;

import com.fpt.fitme.entity.plan.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface PlanRepository extends CrudRepository<Plan, Long> {

    public Plan getPlanByDate(Date date);
}

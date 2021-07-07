package com.fpt.fitme.repository;

import com.fpt.fitme.entity.plan.Plan;
import com.fpt.fitme.entity.target.Target;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface PlanRepository extends CrudRepository<Plan, Long> {

    public Plan getPlanByDateAndTarget(Date date, Target target);
}

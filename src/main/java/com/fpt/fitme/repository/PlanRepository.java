package com.fpt.fitme.repository;

import com.fpt.fitme.entity.plan.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, Long> {
}

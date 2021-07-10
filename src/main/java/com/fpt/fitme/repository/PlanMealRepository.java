package com.fpt.fitme.repository;

import com.fpt.fitme.entity.plan.Plan;
import com.fpt.fitme.entity.plan.PlanMeal;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PlanMealRepository extends CrudRepository<PlanMeal, Long> {

    public Set<PlanMeal> getAllByPlanOrderByIdAsc(Plan plan);
}

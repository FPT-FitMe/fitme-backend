package com.fpt.fitme.repository;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.target.Target;
import org.springframework.data.repository.CrudRepository;

public interface TargetRepository extends CrudRepository<Target, Long> {

    public Target getTargetByTrainee(AppUser trainee);

    public Target getTargetByTraineeAndHasFinished(AppUser trainee, Boolean hasFinished);
}

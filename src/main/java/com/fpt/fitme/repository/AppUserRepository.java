package com.fpt.fitme.repository;

import com.fpt.fitme.entity.appuser.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    public AppUser getAppUserByUsername(String username);

}

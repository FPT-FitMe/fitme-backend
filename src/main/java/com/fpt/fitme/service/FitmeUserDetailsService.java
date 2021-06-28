package com.fpt.fitme.service;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@PropertySource("classpath:constants.properties")
public class FitmeUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private Environment env;


    public UserDetails loadFakeLogin() {
        return new User("fitmeUser", env.getProperty("password123456BCrypt"), new ArrayList<>());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.getAppUserByEmail(email);

        return new User(appUser.getEmail(), appUser.getPassword(), new ArrayList<>());
    }

}

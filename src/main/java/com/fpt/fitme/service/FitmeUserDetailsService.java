package com.fpt.fitme.service;

import com.fpt.fitme.domain.FitmeUserDetails;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@PropertySource("classpath:constants.properties")
public class FitmeUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private Environment env;


    public UserDetails loadFakeLogin() {
        return new User("fitmeUser", env.getProperty("password123456BCrypt"), new ArrayList<>());
    }

    @Override
    public FitmeUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;

        try {
            AppUser appUser = appUserRepository.getAppUserByEmail(email);
            String roleName = appUser.getRole().getRoleName();
            if (roleName.equals("Manager")) {
                roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_MANAGER"));

            } else if (roleName.equals("Trainee")) {
                roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER"));
            }
            return new FitmeUserDetails(new User(appUser.getEmail(), appUser.getPassword(), roles),
                    appUser.getFirstName(), appUser.getLastName(),
                    appUser.getRole().getRoleName(), appUser.getGender() == 1 ? "M" : "F", appUser.getPhone(),
                    appUser.getProfileImageUrl(), appUser.getIsPremium());
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public AppUser save(FitmeUserDetails fitmeUserDetails) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(fitmeUserDetails.getFirstName());
        appUser.setLastName(fitmeUserDetails.getLastName());
        appUser.setEmail(fitmeUserDetails.getUsername());
        appUser.setPassword(fitmeUserDetails.getPassword());
        appUser.setGender(fitmeUserDetails.getGender().equals("M") ? 1 : 0);
        appUser.setProfileImageUrl(fitmeUserDetails.getProfileImageUrl());
        appUser.setIsPremium(fitmeUserDetails.getPremium());

        return appUserRepository.save(appUser);
    }
}

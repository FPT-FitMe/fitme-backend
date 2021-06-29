package com.fpt.fitme.service;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.model.FitMeAppUserDTO;
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
import java.util.Arrays;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;

        try {
            AppUser appUser = appUserRepository.getAppUserByEmail(email);
            String roleName = appUser.getRole().getRoleName();
            if (roleName.equals("Manager")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"));

            } else if (roleName.equals("Trainee")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"));
            }
            return new User(appUser.getEmail(), appUser.getPassword(), roles);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public AppUser save(FitMeAppUserDTO fitMeAppUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(fitMeAppUserDTO.getFirstName());
        appUser.setLastName(fitMeAppUserDTO.getLastName());
        appUser.setEmail(fitMeAppUserDTO.getEmail());
        appUser.setPassword(fitMeAppUserDTO.getPassword());
        appUser.setGender(fitMeAppUserDTO.getGender().equals("M") ? 1 : 0);
        appUser.setProfileImageUrl(fitMeAppUserDTO.getProfileImageUrl());
        appUser.setIsPremium(fitMeAppUserDTO.getPremium());

        return appUserRepository.save(appUser);
    }
}

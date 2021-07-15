package com.fpt.fitme.service;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.model.FitMeUser;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.repository.AppUserRoleRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AppUserRoleRepository appUserRoleRepository;

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

            if (!appUser.getIsActive())
                throw new UsernameNotFoundException("User not found with email: " + email);
            if (roleName.equals("Manager")) {
                roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_MANAGER"));

            } else if (roleName.equals("Trainee")) {
                roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER"));
            }
            return new User(appUser.getEmail(), appUser.getPassword(), roles);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public FitMeUser getUserInfo(String email) {
        AppUser appUser = appUserRepository.getAppUserByEmail(email);
        // Khi moi register xong thi nhung field sau se bi null
        // gender, phone, profileImageUrl
        String gender = null;
        if (appUser.getGender() != null) {
            gender = appUser.getGender() == 0 ? "M" : "F";
        }
        return new FitMeUser(appUser.getUserID(), appUser.getEmail(), appUser.getPassword(), appUser.getFirstName(),
                appUser.getLastName(), gender, appUser.getRole().getRoleID() == 0 ? "ROLE_MEMBER" : "ROLE_MANAGER",
                appUser.getPhone(), appUser.getProfileImageUrl(),
                appUser.getIsPremium());
    }

    public AppUser register(FitMeUser fitMeUser) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(fitMeUser.getFirstName());
        appUser.setLastName(fitMeUser.getLastName());
        appUser.setEmail(fitMeUser.getEmail());
        appUser.setRole(appUserRoleRepository.findById(0).get());
        appUser.setPassword(bcryptEncoder.encode(fitMeUser.getPassword()));
        appUser.setIsActive(true);
        appUser.setIsPremium(false);

        return appUserRepository.save(appUser);
    }

    public AppUser getUserByAuthorization() throws Exception {
        String email;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                email = ((UserDetails)principal).getUsername();
                AppUser appUser = appUserRepository.getAppUserByEmail(email);
                if (appUser == null) {
                    throw new NotFoundException("No such user found");
                }
                return appUser;
            } else {
                throw new UsernameNotFoundException("Cannot get user from current session");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

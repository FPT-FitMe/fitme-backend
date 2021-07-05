package com.fpt.fitme.controller;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.service.FitmeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainee")
public class TraineeController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private FitmeUserDetailsService fitmeUserDetailsService;

    @Autowired
    private Environment env;

    @PostMapping("/buySubscription")
    public ResponseEntity buySubscription() {
        String email = null;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                email = ((UserDetails)principal).getUsername();
                AppUser appUser = appUserRepository.getAppUserByEmail(email);
                if (appUser.getIsPremium()) {
                    return new ResponseEntity("User already subscribed", HttpStatus.BAD_REQUEST);
                }
                appUser.setIsPremium(true);
            } else {
                return new ResponseEntity("Invalid credentials", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity("Error ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(fitmeUserDetailsService.getUserInfo(email), HttpStatus.OK);
    }

}

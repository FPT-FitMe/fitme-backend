package com.fpt.fitme.service;

import com.fpt.fitme.dto.appUser.AppUserDTO;
import com.fpt.fitme.dto.appUser.AppUserStatusDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.repository.AppUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    public List<AppUserDTO> getAllUsers() {
        List<AppUserDTO> results = new ArrayList<>();
        appUserRepository.findAll().forEach(appUser -> {
            if (appUser.getIsActive()) {
                AppUserDTO dto = modelMapper.map(appUser, AppUserDTO.class);
                results.add(dto);
            }
        });
        return results;
    }

    public AppUserDTO getAppUserById(long id) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(id);
        if (appUserOptional.isPresent() && appUserOptional.get().getIsActive())
            return modelMapper.map(appUserOptional.get(), AppUserDTO.class);
        return null;
    }

    public AppUserStatusDTO changeAppUserStatus(Long id, boolean isActive) throws Exception {
        Optional<AppUser> currentAppUser = appUserRepository.findById(id);


        AppUser appUserPatched = null;
        if (currentAppUser.isPresent()) {
            appUserPatched = currentAppUser.get();
            if (isActive && !appUserPatched.getIsActive()) {
                appUserPatched.setIsActive(true);
                appUserRepository.save(appUserPatched);
            } else {
                if (appUserPatched.getIsActive()) {
                    appUserPatched.setIsActive(isActive);
                    appUserRepository.save(appUserPatched);
                } else {
                    throw new Exception("No such app user found");
                }
            }
        }
        return modelMapper.map(appUserPatched, AppUserStatusDTO.class);
    }

    public AppUserDTO updateUser(Long id, AppUser appUser) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);

        if (optionalAppUser.isPresent() && optionalAppUser.get().getIsActive()) {
            AppUser appUserToUpdate = optionalAppUser.get();
            appUserToUpdate.setFirstName(appUser.getFirstName());
            appUserToUpdate.setLastName(appUser.getLastName());
            appUserToUpdate.setPassword(bcryptEncoder.encode(appUser.getPassword()));
            appUserToUpdate.setEmail(appUser.getEmail());
            appUserToUpdate.setPhone(appUser.getPhone());
            appUserToUpdate.setAge(appUser.getAge());
            appUserToUpdate.setRole(appUser.getRole());
            appUserToUpdate.setGender(appUser.getGender());
            appUserToUpdate.setTraineeFavoriteWorkouts(appUser.getTraineeFavoriteWorkouts());
            appUserToUpdate.setTraineeFavoriteMeals(appUser.getTraineeFavoriteMeals());
            appUserToUpdate.setHeight(appUser.getHeight());
            appUserToUpdate.setProfileImageUrl(appUser.getProfileImageUrl());
            appUserToUpdate.setDietPreferenceType(appUser.getDietPreferenceType());
            appUserToUpdate.setExerciseFrequencyType(appUser.getExerciseFrequencyType());
            appUserToUpdate.setWorkoutIntensity(appUser.getWorkoutIntensity());
            appUserRepository.save(appUserToUpdate);
            return modelMapper.map(appUserToUpdate, AppUserDTO.class);
        } else {
            return null;
        }
    }
}

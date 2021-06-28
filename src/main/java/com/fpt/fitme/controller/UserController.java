package com.fpt.fitme.controller;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.util.JsonPatcherUtil;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    AppUserRepository appUserRepository;


    @GetMapping("")
    public ResponseEntity<List<AppUser>> getAllAppUser() {
        List<AppUser> result = new ArrayList<>();
        appUserRepository.findAll().forEach(result::add);
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getAppUserByID(@PathVariable("id") long id) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(id);

        if (appUserOptional.isPresent()) {
            return new ResponseEntity<>(appUserOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity addUser(@RequestBody AppUser appUser) {
        try {
            //lay password ra encrypt
            AppUser savedAppUser = appUserRepository.save(appUser);
            return new ResponseEntity(savedAppUser, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                return new ResponseEntity("Username is duplicated", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchUser(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            Optional<AppUser> currentAppUser = appUserRepository.findById(id);

            if (currentAppUser.isPresent()) {
                AppUser appUserPatched = (AppUser) JsonPatcherUtil.applyPatch(patch, currentAppUser.get());
                appUserRepository.save(appUserPatched);
                return ResponseEntity.ok(appUserPatched);
            }  else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody AppUser appUser) {
        Optional<AppUser> optionalAppUser = appUserRepository.findById(id);

        if (optionalAppUser.isPresent()) {
            AppUser appUserToUpdate = optionalAppUser.get();
            appUserToUpdate.setFirstName(appUser.getFirstName());
            appUserToUpdate.setLastName(appUser.getLastName());
            appUserToUpdate.setPassword(appUser.getPassword());
            appUserToUpdate.setEmail(appUser.getEmail());
            appUserToUpdate.setPhone(appUser.getPhone());
            appUserToUpdate.setAge(appUser.getAge());
            appUserToUpdate.setRole(appUser.getRole());
            appUserToUpdate.setGender(appUser.getGender());
            appUserToUpdate.setTraineeFavoriteWorkouts(appUser.getTraineeFavoriteWorkouts());
            appUserToUpdate.setTraineeFavoriteMeals(appUser.getTraineeFavoriteMeals());
            appUserToUpdate.setHeight(appUser.getHeight());
            appUserToUpdate.setDietPreferenceType(appUser.getDietPreferenceType());
            appUserToUpdate.setExerciseFrequencyType(appUser.getExerciseFrequencyType());
            appUserToUpdate.setWorkoutIntensity(appUser.getWorkoutIntensity());
            return new ResponseEntity(appUserRepository.save(appUserToUpdate), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/completeSurvey?{id}")
    public void updateUserProfileOnSurvey(@PathVariable("id") Long id, @RequestBody AppUser appUser) {

    }
}

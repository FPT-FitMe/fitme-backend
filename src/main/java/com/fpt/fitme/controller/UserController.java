package com.fpt.fitme.controller;

import com.fpt.fitme.dto.appUser.AppUserDTO;
import com.fpt.fitme.dto.appUser.DisabledAppUserDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.service.AppUserService;
import com.github.fge.jsonpatch.JsonPatch;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("")
    public ResponseEntity<List<AppUserDTO>> getAllAppUser() {
        List<AppUserDTO> result = appUserService.getAllUsers();
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getAppUserByID(@PathVariable("id") long id) {
        AppUserDTO appUserDTO = appUserService.getAppUserById(id);

        if (appUserDTO != null) {
            return new ResponseEntity<>(appUserDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity disableUser(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            DisabledAppUserDTO disableUser = appUserService.disabledAppUser(id, patch);
            return new ResponseEntity(disableUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody AppUser appUser) {
        try {
            AppUserDTO appUserDTO = appUserService.updateUser(id, appUser);
            if (appUserDTO != null) {
                return new ResponseEntity(appUserDTO, HttpStatus.OK);
            } else {
                throw new NotFoundException("No such user found!");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

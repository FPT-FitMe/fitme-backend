package com.fpt.fitme.controller;

import com.fpt.fitme.dto.CoachProfile.DisableCoachProfileDTO;
import com.fpt.fitme.dto.CoachProfile.CoachProfileDTO;
import com.fpt.fitme.entity.workout.CoachProfile;
import com.fpt.fitme.service.CoachProfileService;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coachs")
public class CoachProfileController {

    @Autowired
    private CoachProfileService coachService;

    @GetMapping("")
    public ResponseEntity<List<CoachProfileDTO>> getAllCoachProfiles() {
        List<CoachProfileDTO> result = coachService.getListCoach();
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoachProfileDTO> getCoachProfileByID(@PathVariable("id") long id) {
        CoachProfileDTO dto= coachService.getCoachProfileByID(id);

        if (dto!=null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity createCoachProfile(@RequestBody CoachProfile coach) {
        try {
            CoachProfileDTO dto=coachService.createCoachProfile(coach);
            return new ResponseEntity(dto,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchCoachProfile(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            DisableCoachProfileDTO dto=coachService.disableCoachProfile(id,patch);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@PathVariable("id") Long id, @RequestBody CoachProfile coach) {
        try {
            CoachProfileDTO dto=coachService.updateCoachProfile(id,coach);
            if(dto!=null){
                return new ResponseEntity(dto,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

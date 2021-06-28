package com.fpt.fitme.controller;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.plan.Plan;
import com.fpt.fitme.entity.target.Target;
import com.fpt.fitme.repository.*;
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
@RequestMapping("/targets")
public class TargetController {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TargetRepository targetRepository;

    @GetMapping("")
    public ResponseEntity<List<Target>> getAllTargets() {
        List<Target> result = new ArrayList<>();
        targetRepository.findAll().forEach(result::add);
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Target> getTargetById(@PathVariable("id") long id) {
        Optional<Target> targetOptional = targetRepository.findById(id);

        if (targetOptional.isPresent()) {
            return new ResponseEntity<>(targetOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity addPlan(@RequestBody Target target) {
        try {
            Optional<AppUser> trainee = appUserRepository.findById(target.getTrainee().getUserID());
            if (trainee.isPresent()) {
                target.setTrainee(trainee.get());
                Target savedTarget = targetRepository.save(target);
                return new ResponseEntity(savedTarget, HttpStatus.CREATED);
            }
            return new ResponseEntity("Check other relation's ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTarget(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        try {
            Optional<Plan> currentPlan = planRepository.findById(id);

            if (currentPlan.isPresent()) {
                Plan planPatched = (Plan) JsonPatcherUtil.applyPatch(patch, currentPlan.get());
                planRepository.save(planPatched);
                return ResponseEntity.ok(planPatched);
            }  else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTarget(@PathVariable("id") Long id, @RequestBody Target target) {
        Optional<Target> optionalTarget = targetRepository.findById(id);

        if (optionalTarget.isPresent()) {
            Target targetToUpdate = optionalTarget.get();
            targetToUpdate.setTrainee(appUserRepository.findById(target.getTrainee().getUserID()).get());
            targetToUpdate.setTargetBMI(target.getTargetBMI());
            targetToUpdate.setStartingBMI(target.getStartingBMI());
            targetToUpdate.setStartDate(target.getStartDate());
            targetToUpdate.setCompleteDate(target.getCompleteDate());
            targetToUpdate.setHasFinished(targetToUpdate.getHasFinished());
            Target savedTarget = targetRepository.save(targetToUpdate);
            return new ResponseEntity<>(savedTarget, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

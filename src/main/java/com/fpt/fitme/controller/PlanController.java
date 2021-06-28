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
@RequestMapping("/plans")
public class PlanController {

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
    public ResponseEntity<List<Plan>> getAllPlans() {
        List<Plan> result = new ArrayList<>();
        planRepository.findAll().forEach(result::add);
        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable("id") long id) {
        Optional<Plan> planOptional = planRepository.findById(id);

        if (planOptional.isPresent()) {
            return new ResponseEntity<>(planOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity addPlan(@RequestBody Plan plan) {
        try {
            Optional<Target> target = targetRepository.findById(plan.getTarget().getId());
            if (target.isPresent()) {
                plan.setPlanMeals(plan.getPlanMeals());
                plan.setPlanWorkouts(plan.getPlanWorkouts());
                plan.setTarget(target.get());
                Plan savedPlan = planRepository.save(plan);
                return new ResponseEntity(savedPlan, HttpStatus.CREATED);
            }
            return new ResponseEntity("Check other relation's ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchPlan(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
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
    public ResponseEntity updatePlan(@PathVariable("id") Long id, @RequestBody Plan plan) {
        Optional<Plan> optionalPlan = planRepository.findById(id);

        if (optionalPlan.isPresent()) {
            Plan planToUpdate = optionalPlan.get();
            planToUpdate.setTarget(plan.getTarget());
            planToUpdate.setPlanMeals(plan.getPlanMeals());
            planToUpdate.setPlanWorkouts(plan.getPlanWorkouts());
            planToUpdate.setDate(plan.getDate());
            Plan savedPlan = planRepository.save(planToUpdate);
            return new ResponseEntity<>(savedPlan, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/build?{userId}")
    public ResponseEntity buildPlanForTrainee(@PathVariable("userId") Long id, @RequestBody Target target) {
        Optional<AppUser> trainee = appUserRepository.findById(id);

        Target currentTarget = targetRepository.getTargetByTrainee(trainee.get());
        return null;
    }
}

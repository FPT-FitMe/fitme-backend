package com.fpt.fitme.service;

import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.entity.workout.Workout;
import com.fpt.fitme.repository.AppUserRepository;
import com.fpt.fitme.repository.MealRepository;
import com.fpt.fitme.repository.WorkoutRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TraineeService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    public void favoriteWorkout(AppUser trainee, Long workoutId) throws Exception {
        Set<Workout> traineeFavoriteWorkouts = trainee.getTraineeFavoriteWorkouts();
        Optional<Workout> workout = workoutRepository.findById(workoutId);
        if (workout.isPresent() && workout.get().getIsActive()) {
            traineeFavoriteWorkouts.add(workout.get());
            appUserRepository.save(trainee);
        } else {
            throw new NotFoundException("Workout not found");
        }
    }

    public void favoriteMeal(AppUser trainee, Long mealId) throws Exception {
        Set<Meal> traineeFavoriteMeals = trainee.getTraineeFavoriteMeals();
        Optional<Meal> meal = mealRepository.findById(mealId);
        if (meal.isPresent() && meal.get().getIsActive()) {
            traineeFavoriteMeals.add(meal.get());
            appUserRepository.save(trainee);
        } else {
            throw new NotFoundException("Meal not found");
        }
    }
}

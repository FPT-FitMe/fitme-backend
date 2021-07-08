package com.fpt.fitme.service;

import com.fpt.fitme.dto.meal.DisableMealDTO;
import com.fpt.fitme.dto.meal.MealDTO;
import com.fpt.fitme.entity.appuser.AppUser;
import com.fpt.fitme.entity.meal.Meal;
import com.fpt.fitme.entity.tag.Tag;
import com.fpt.fitme.entity.workout.CoachProfile;
import com.fpt.fitme.repository.CoachProfileRepository;
import com.fpt.fitme.repository.MealRepository;
import com.fpt.fitme.repository.TagRepository;
import com.fpt.fitme.util.JsonPatcherUtil;
import com.github.fge.jsonpatch.JsonPatch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private CoachProfileRepository coachProfileRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private FitmeUserDetailsService fitmeUserDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    public List<MealDTO> getListMeal() {
        List<MealDTO> result = new ArrayList<>();
        mealRepository.findAll().forEach(meal -> {
            if (meal.getIsActive()) {
                MealDTO dto = modelMapper.map(meal, MealDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public List<MealDTO> getListMealByTagID(long tagID) throws Exception{
        List<MealDTO> result = new ArrayList<>();
        Optional<Tag> tag=tagRepository.findById(tagID);
        if(!(tag.isPresent()&&tag.get().getIsActive())) throw new Exception("tagID not found!");
        mealRepository.findMealsByTags(tag.get()).forEach(meal -> {
            if (meal.getIsActive()) {
                MealDTO dto = modelMapper.map(meal, MealDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public List<MealDTO> getListMealByCoachID(long coachID) throws Exception{
        List<MealDTO> result = new ArrayList<>();
        Optional<CoachProfile> coachProfile=coachProfileRepository.findById(coachID);
        if(!(coachProfile.isPresent()&&coachProfile.get().getIsActive())) throw new Exception("coachID not found!");
        mealRepository.getMealsByCoachProfile(coachProfile.get()).forEach(meal -> {
            if (meal.getIsActive()) {
                MealDTO dto = modelMapper.map(meal, MealDTO.class);
                result.add(dto);
            }
        });
        return result;
    }

    public MealDTO getMealByID(long id) {
        Optional<Meal> mealOptional = mealRepository.findById(id);
        if (mealOptional.isPresent() && mealOptional.get().getIsActive())
            return modelMapper.map(mealOptional.get(), MealDTO.class);
        return null;
    }

    public MealDTO createMeal(Meal meal) throws Exception {
        AppUser appUser=fitmeUserDetailsService.getUserByAuthorization();
        Optional<CoachProfile> coachProfile = coachProfileRepository.findById(meal.getCoachProfile().getCoachID());

        if (!coachProfile.isPresent()) throw new Exception("coachID not found!");

        Set<Tag> tags = new HashSet<>();
        for (Tag tag : meal.getTags()) {
            Optional<Tag> tagToAdd = tagRepository.findById(tag.getId());
            if (tagToAdd.isPresent()) {
                tags.add(tagToAdd.get());
            }
        }

        meal.setCoachProfile(coachProfile.get());
        meal.setCreator(appUser);
        meal.setTags(tags);
        meal.setIsActive(true);

        Meal savedMeal = mealRepository.save(meal);
        return modelMapper.map(savedMeal, MealDTO.class);
    }

    public MealDTO patchMeal(Long id, JsonPatch patch) throws Exception {
        Optional<Meal> currentMeal = mealRepository.findById(id);

        if (!(currentMeal.isPresent() && currentMeal.get().getIsActive()))
            throw new Exception("mealID not found!");

        Meal mealPatched = (Meal) JsonPatcherUtil.applyPatch(patch, currentMeal.get());
        mealRepository.save(mealPatched);
        return modelMapper.map(mealPatched, MealDTO.class);
    }

    public DisableMealDTO disableMeal(Long id, JsonPatch patch) throws Exception {
        Optional<Meal> currentMeal = mealRepository.findById(id);

        if (!(currentMeal.isPresent() && currentMeal.get().getIsActive()))
            throw new Exception("mealID not found!");

        Meal mealPatched = (Meal) JsonPatcherUtil.applyPatch(patch, currentMeal.get());
        mealRepository.save(mealPatched);

        return modelMapper.map(mealPatched, DisableMealDTO.class);
    }

    public MealDTO updateMeal(Long id, Meal meal) throws Exception {
        Optional<Meal> optionalMeal = mealRepository.findById(id);

        if (!(optionalMeal.isPresent() && optionalMeal.get().getIsActive())) throw new Exception("mealID not found!");
        Meal mealToUpdate = optionalMeal.get();
        mealToUpdate.setName(meal.getName());
        mealToUpdate.setDescription(meal.getDescription());
        mealToUpdate.setTags(meal.getTags());
        mealToUpdate.setCookingTime(meal.getCookingTime());
        mealToUpdate.setIsPremium(meal.getIsPremium());
        mealToUpdate.setImageUrl(meal.getImageUrl());
        mealToUpdate.setCalories(meal.getCalories());
        mealToUpdate.setCarbAmount(meal.getCarbAmount());
        mealToUpdate.setFatAmount(meal.getFatAmount());
        mealRepository.save(mealToUpdate);

        return modelMapper.map(mealToUpdate, MealDTO.class);

    }
}

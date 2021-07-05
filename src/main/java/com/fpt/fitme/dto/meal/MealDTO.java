package com.fpt.fitme.dto.meal;

import com.fpt.fitme.dto.AppUserDTO;
import com.fpt.fitme.dto.CoachProfileDTO;
import com.fpt.fitme.dto.TagDTO;

import java.util.Date;
import java.util.Set;

public class MealDTO {
    private Long mealID;
    private AppUserDTO creator;
    private CoachProfileDTO coachProfile;
    private Set<TagDTO> tags;
    private String name;
    private String description;
    private Integer cookingTime;
    private Boolean isPremium;
    private String imageUrl;
    private Float calories;
    private Float carbAmount;
    private Float fatAmount;
    private Date createdDate;
    private Date lastModifiedDate;

    public Long getMealID() {
        return mealID;
    }

    public void setMealID(Long mealID) {
        this.mealID = mealID;
    }

    public AppUserDTO getCreator() {
        return creator;
    }

    public void setCreator(AppUserDTO creator) {
        this.creator = creator;
    }

    public CoachProfileDTO getCoachProfile() {
        return coachProfile;
    }

    public void setCoachProfile(CoachProfileDTO coachProfile) {
        this.coachProfile = coachProfile;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Boolean getPremium() {
        return isPremium;
    }

    public void setPremium(Boolean premium) {
        isPremium = premium;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Float getCarbAmount() {
        return carbAmount;
    }

    public void setCarbAmount(Float carbAmount) {
        this.carbAmount = carbAmount;
    }

    public Float getFatAmount() {
        return fatAmount;
    }

    public void setFatAmount(Float fatAmount) {
        this.fatAmount = fatAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

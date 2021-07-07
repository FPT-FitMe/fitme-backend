package com.fpt.fitme.dto.workout;

import com.fpt.fitme.dto.appUser.AppUserDTO;
import com.fpt.fitme.dto.coachprofile.CoachProfileDTO;
import com.fpt.fitme.dto.TagDTO;
import com.fpt.fitme.dto.workoutexercise.WorkoutExerciseDTO;

import java.util.Date;
import java.util.Set;

public class WorkoutDTO {
    private Long workoutID;
    private String name;
    private CoachProfileDTO coachProfile;
    private AppUserDTO creator;
    private String description;
    private Set<TagDTO> tags;
    private Set<WorkoutExerciseDTO> workout_exercises;
    private Integer estimatedDuration;
    private Integer estimatedCalories;
    private Integer level;
    private Boolean isPremium;
    private String imageUrl;
    private Date createdDate;
    private Date lastModifiedDate;

    public Long getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(Long workoutID) {
        this.workoutID = workoutID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoachProfileDTO getCoachProfile() {
        return coachProfile;
    }

    public void setCoachProfile(CoachProfileDTO coachProfile) {
        this.coachProfile = coachProfile;
    }

    public AppUserDTO getCreator() {
        return creator;
    }

    public void setCreator(AppUserDTO creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    public Set<WorkoutExerciseDTO> getWorkout_exercises() {
        return workout_exercises;
    }

    public void setWorkout_exercises(Set<WorkoutExerciseDTO> workout_exercises) {
        this.workout_exercises = workout_exercises;
    }

    public Integer getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Integer estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Integer getEstimatedCalories() {
        return estimatedCalories;
    }

    public void setEstimatedCalories(Integer estimatedCalories) {
        this.estimatedCalories = estimatedCalories;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

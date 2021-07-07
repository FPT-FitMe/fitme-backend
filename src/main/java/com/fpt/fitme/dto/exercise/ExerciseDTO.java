package com.fpt.fitme.dto.exercise;


import com.fpt.fitme.dto.appUser.AppUserDTO;
import com.fpt.fitme.dto.tag.TagDTO;


import java.util.Date;
import java.util.Set;

public class ExerciseDTO {
    private Long exerciseID;
    private Set<TagDTO> tags;
    private String name;
    private String description;
    private String videoUrl;
    private Integer baseDuration;
    private Integer baseRepPerRound;
    private Integer baseKcal;
    private String imageUrl;
    private AppUserDTO creator;
    private Date createdDate;
    private Date lastModifiedDate;

    public Long getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(Long exerciseID) {
        this.exerciseID = exerciseID;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getBaseDuration() {
        return baseDuration;
    }

    public void setBaseDuration(Integer baseDuration) {
        this.baseDuration = baseDuration;
    }

    public Integer getBaseRepPerRound() {
        return baseRepPerRound;
    }

    public void setBaseRepPerRound(Integer baseRepPerRound) {
        this.baseRepPerRound = baseRepPerRound;
    }

    public Integer getBaseKcal() {
        return baseKcal;
    }

    public void setBaseKcal(Integer baseKcal) {
        this.baseKcal = baseKcal;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AppUserDTO getCreator() {
        return creator;
    }

    public void setCreator(AppUserDTO creator) {
        this.creator = creator;
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

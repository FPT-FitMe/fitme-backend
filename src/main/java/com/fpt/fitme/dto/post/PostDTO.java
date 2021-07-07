package com.fpt.fitme.dto.post;

import com.fpt.fitme.dto.appUser.AppUserDTO;
import com.fpt.fitme.dto.coachprofile.CoachProfileDTO;

import java.util.Date;

public class PostDTO {
    private Long postID;
    private AppUserDTO creator;
    private CoachProfileDTO coachProfile;
    private String name;
    private String contentHeader;
    private String contentBody;
    private Integer readingTime;
    private String imageUrl;
    private Date createdDate;
    private Date lastModifiedDate;

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentHeader() {
        return contentHeader;
    }

    public void setContentHeader(String contentHeader) {
        this.contentHeader = contentHeader;
    }

    public String getContentBody() {
        return contentBody;
    }

    public void setContentBody(String contentBody) {
        this.contentBody = contentBody;
    }

    public Integer getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(Integer readingTime) {
        this.readingTime = readingTime;
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
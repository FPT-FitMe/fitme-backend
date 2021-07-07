package com.fpt.fitme.dto.coachprofile;

public class CoachProfileDTO {
    private Long coachID;
    private String name;
    private String introduction;
    private String contact;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Long getCoachID() {
        return coachID;
    }

    public void setCoachID(Long coachID) {
        this.coachID = coachID;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

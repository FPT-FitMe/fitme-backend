package com.fpt.fitme.dto.coachprofile;

public class DisableCoachProfileDTO {
    private Long coachID;
    private Boolean isActive;

    public Long getCoachID() {
        return coachID;
    }

    public void setCoachID(Long coachID) {
        this.coachID = coachID;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

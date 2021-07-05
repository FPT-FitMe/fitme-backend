package com.fpt.fitme.dto.meal;

public class DisableMealDTO {
    private Long mealID;
    private Boolean isActive;

    public Long getMealID() {
        return mealID;
    }

    public void setMealID(Long mealID) {
        this.mealID = mealID;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

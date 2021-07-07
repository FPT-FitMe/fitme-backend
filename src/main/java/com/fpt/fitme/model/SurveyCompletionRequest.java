package com.fpt.fitme.model;

public class SurveyCompletionRequest {

    private int gender;
    private int age;
    private float heightInCm;
    private float weightInKg;
    private long dietPreferenceType;
    private float targetWeightInKg;
    private int durationInDays;
    private long exerciseFrequencyType;

    public SurveyCompletionRequest() {
    }

    public SurveyCompletionRequest(int gender, int age, float heightInCm, float weightInKg,
                                   long dietPreferenceType, float targetWeightInKg, int durationInDays, long exerciseFrequencyType) {
        this.gender = gender;
        this.age = age;
        this.heightInCm = heightInCm;
        this.weightInKg = weightInKg;
        this.dietPreferenceType = dietPreferenceType;
        this.targetWeightInKg = targetWeightInKg;
        this.durationInDays = durationInDays;
        this.exerciseFrequencyType = exerciseFrequencyType;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(float heightInMeter) {
        this.heightInCm = heightInMeter;
    }

    public float getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(float weightInKg) {
        this.weightInKg = weightInKg;
    }

    public long getDietPreferenceType() {
        return dietPreferenceType;
    }

    public void setDietPreferenceType(long dietPreferenceType) {
        this.dietPreferenceType = dietPreferenceType;
    }

    public float getTargetWeightInKg() {
        return targetWeightInKg;
    }

    public void setTargetWeightInKg(float targetWeight) {
        this.targetWeightInKg = targetWeight;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public long getExerciseFrequencyType() {
        return exerciseFrequencyType;
    }

    public void setExerciseFrequencyType(long exerciseFrequencyType) {
        this.exerciseFrequencyType = exerciseFrequencyType;
    }
}

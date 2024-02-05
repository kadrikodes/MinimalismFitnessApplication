package com.health.minimalismfitnessapp.backend.entities;

import jakarta.persistence.*;

@Entity
public class PushUpData {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ActivityData activityData;
    private int numberOfPushUps;
    private int target;
    private double timeDuration;
    private int caloriesBurnt;
    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    public PushUpData(int numberOfPushUps, int target, double timeDuration, int caloriesBurnt, UserData userData, ActivityData activityData) {
        this.numberOfPushUps = numberOfPushUps;
        this.target = target;
        this.timeDuration = timeDuration;
        this.caloriesBurnt = caloriesBurnt;
        this.userData = userData;
        this.activityData = activityData;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserData getUser() {
        return userData;
    }

    public void setUser(UserData userData) {
        this.userData = userData;
    }

    public int getNumberOfPushUps() {
        return numberOfPushUps;
    }

    public void setNumberOfPushUps(int numberOfPushUps) {
        this.numberOfPushUps = numberOfPushUps;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public double getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(double timeDuration) {
        this.timeDuration = timeDuration;
    }

    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(int caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }
    public ActivityData getActivityData() {
        return activityData;
    }

    public void setActivityData(ActivityData activityData) {
        this.activityData = activityData;
    }
}

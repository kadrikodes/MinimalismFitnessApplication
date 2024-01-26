package com.health.minimalismfitnessapp.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PushUpData {

    @Id
    @GeneratedValue
    private long id;
    private int numberOfPushUps;
    private int target;
    private double timeDuration;
    private int caloriesBurnt;
    @ManyToOne
    private UserData userData;

    public PushUpData() {}
    public PushUpData(int numberOfPushUps, int target, double timeDuration, int caloriesBurnt, UserData userData) {
        this.numberOfPushUps = numberOfPushUps;
        this.target = target;
        this.timeDuration = timeDuration;
        this.caloriesBurnt = caloriesBurnt;
        this.userData = userData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}

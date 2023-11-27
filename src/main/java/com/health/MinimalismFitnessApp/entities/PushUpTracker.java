package com.health.MinimalismFitnessApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PushUpTracker {

    @Id
    @GeneratedValue
    private int numberOfPushUps;
    private int target;
    private double timeDuration;
    private int caloriesBurnt;

    public PushUpTracker() {}

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

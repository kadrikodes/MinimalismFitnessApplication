package com.health.MinimalismFitnessApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PushUpTracker {

    @Id
    @GeneratedValue
    private long id;
    private int numberOfPushUps;
    private int target;
    private double timeDuration;
    private int caloriesBurnt;
    @ManyToOne
    private User user;

    public PushUpTracker() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

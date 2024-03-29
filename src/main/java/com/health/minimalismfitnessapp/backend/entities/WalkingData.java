package com.health.minimalismfitnessapp.backend.entities;


import jakarta.persistence.*;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class WalkingData {
    @Id
    @GeneratedValue
    private Long id;
    private int steps;
    private double distance;
    private int caloriesBurned;
    private double duration;
    private double speed;
    private LocalDateTime dateTime;


    @ManyToOne
    @JoinColumn(name = "activity_data_id")
    private ActivityData activityData;

    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    public WalkingData() {}
    public WalkingData(int steps, double distance, int caloriesBurned, double duration, double speed, LocalDateTime dateTime, UserData userData, ActivityData activityData) {
        this.steps = steps;
        this.distance = distance;
        this.caloriesBurned = caloriesBurned;
        this.duration = duration;
        this.speed = speed;
        this.dateTime = dateTime;
        this.userData = userData;
        this.activityData = activityData;

    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

    }
    public ActivityData getActivityData() {
        return activityData;
    }

    public void setActivityData(ActivityData activityData) {
        this.activityData = activityData;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

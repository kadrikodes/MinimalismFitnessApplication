package com.health.MinimalismFitnessApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class WalkingData {
    @Id
    @GeneratedValue
    private Long Id;
    private int steps;
    private double distance;
    private int caloriesBurned;
    private double duration;
    private double speed;
    private LocalDateTime dateTime;

    @ManyToOne
    private UserData userData;

    public WalkingData() {}
    public WalkingData(int steps, double distance, int caloriesBurned, double duration, double speed, LocalDateTime dateTime, UserData userData) {
        this.steps = steps;
        this.distance = distance;
        this.caloriesBurned = caloriesBurned;
        this.duration = duration;
        this.speed = speed;
        this.dateTime = dateTime;
        this.userData = userData;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

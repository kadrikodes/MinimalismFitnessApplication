package com.health.MinimalismFitnessApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class WalkingTracker {
    @Id
    @GeneratedValue
    private Long Id;
    private int steps;


    private double distance;
    private int caloriesBurned;
    private double duration;
    private double speed;
    private LocalDateTime time;

    public WalkingTracker() {}
    public WalkingTracker(int steps, double distance, int caloriesBurned, double duration, double speed, LocalDateTime time) {
        this.steps = steps;
        this.distance = distance;
        this.caloriesBurned = caloriesBurned;
        this.duration = duration;
        this.speed = speed;
        this.time = time;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

package com.health.minimalismfitnessapp.backend.entities;

public class Activity {
    private int id;
    private String workout;
    private double calories;

    public Activity(String workout, double calories) {
        this.workout = workout;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}

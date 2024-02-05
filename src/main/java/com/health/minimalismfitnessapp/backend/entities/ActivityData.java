package com.health.minimalismfitnessapp.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ActivityData {
    @Id
    @GeneratedValue
    private Long id;
    private String workout;

    public ActivityData(){}
    public ActivityData(String workout) {
        this.workout = workout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

}

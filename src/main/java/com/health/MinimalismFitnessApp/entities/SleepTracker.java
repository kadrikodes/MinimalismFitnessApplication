package com.health.MinimalismFitnessApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalTime;

@Entity
public class SleepTracker {

    @Id
    private Long id;
    private LocalTime targetBedtime;
    private LocalTime targetWakeUpTime;
    private LocalTime actualBedtime;
    private LocalTime actualWakeupTime;

    @ManyToOne
    private User user;

    public SleepTracker(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTargetBedtime() {
        return targetBedtime;
    }

    public LocalTime getTargetWakeUpTime() {
        return targetWakeUpTime;
    }

    public LocalTime getActualBedtime() {
        return actualBedtime;
    }

    public LocalTime getActualWakeupTime() {
        return actualWakeupTime;
    }

    public void calculateSleepHours()
    {

    }

    public void inferenceOfSleep()
    {

    }

}

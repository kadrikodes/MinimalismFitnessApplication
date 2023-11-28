package com.health.MinimalismFitnessApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalTime;

@Entity
public class SleepTracker {

    @Id
    @GeneratedValue
    private Long id;
    private LocalTime targetBedtime;
    private LocalTime targetWakeUpTime;
    private LocalTime actualBedtime;
    private LocalTime actualWakeupTime;

    @ManyToOne
    private User user;

    public SleepTracker(LocalTime targetBedtime, LocalTime targetWakeUpTime, LocalTime actualBedtime, LocalTime actualWakeupTime, User user) {
        this.targetBedtime = targetBedtime;
        this.targetWakeUpTime = targetWakeUpTime;
        this.actualBedtime = actualBedtime;
        this.actualWakeupTime = actualWakeupTime;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalTime getTargetBedtime() {
        return targetBedtime;
    }

    public void setTargetBedtime(LocalTime targetBedtime) {
        this.targetBedtime = targetBedtime;
    }

    public LocalTime getTargetWakeUpTime() {
        return targetWakeUpTime;
    }

    public void setTargetWakeUpTime(LocalTime targetWakeUpTime) {
        this.targetWakeUpTime = targetWakeUpTime;
    }

    public LocalTime getActualBedtime() {
        return actualBedtime;
    }

    public void setActualBedtime(LocalTime actualBedtime) {
        this.actualBedtime = actualBedtime;
    }

    public LocalTime getActualWakeupTime() {
        return actualWakeupTime;
    }

    public void setActualWakeupTime(LocalTime actualWakeupTime) {
        this.actualWakeupTime = actualWakeupTime;
    }

    public void calculateSleepHours()
    {

    }

    public void inferenceOfSleep()
    {

    }
}

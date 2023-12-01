package com.health.MinimalismFitnessApp.entities;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class SleepData {

    @Id
    @GeneratedValue(generator = "sleep_data_sequence")
    @SequenceGenerator(name="sleep_data_sequence", initialValue = 100)
    private Long id;
    private LocalTime targetBedtime;
    private LocalTime targetWakeUpTime;
    private LocalTime actualBedtime;
    private LocalTime actualWakeupTime;


    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    public SleepData(){

    }

    public SleepData(LocalTime targetBedtime, LocalTime targetWakeUpTime, LocalTime actualBedtime, LocalTime actualWakeupTime, UserData userData) {
        this.targetBedtime = targetBedtime;
        this.targetWakeUpTime = targetWakeUpTime;
        this.actualBedtime = actualBedtime;
        this.actualWakeupTime = actualWakeupTime;
        this.userData = userData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserData getUser() {
        return userData;
    }

    public void setUser(UserData userData) {
        this.userData = userData;
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



}

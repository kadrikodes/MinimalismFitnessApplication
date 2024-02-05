package com.health.minimalismfitnessapp.backend.entities;

import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class SleepData {

    @Id
    @GeneratedValue(generator = "sleep_data_sequence")
//    @SequenceGenerator(name="sleep_data_sequence", initialValue = 100)
    private Long id;
    private LocalDateTime targetBedtime;
    private LocalDateTime targetWakeUpTime;
    private LocalDateTime actualBedtime;
    private LocalDateTime actualWakeupTime;


    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    public SleepData(){

    }

    public SleepData(LocalDateTime targetBedtime, LocalDateTime targetWakeUpTime, LocalDateTime actualBedtime, LocalDateTime actualWakeupTime, UserData userData) {
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

    public LocalDateTime getTargetBedtime() {
        return targetBedtime;
    }

    public void setTargetBedtime(LocalDateTime targetBedtime) {
        this.targetBedtime = targetBedtime;
    }

    public LocalDateTime getTargetWakeUpTime() {
        return targetWakeUpTime;
    }

    public void setTargetWakeUpTime(LocalDateTime targetWakeUpTime) {
        this.targetWakeUpTime = targetWakeUpTime;
    }

    public LocalDateTime getActualBedtime() {
        return actualBedtime;
    }

    public void setActualBedtime(LocalDateTime actualBedtime) {
        this.actualBedtime = actualBedtime;
    }

    public LocalDateTime getActualWakeupTime() {
        return actualWakeupTime;
    }

    public void setActualWakeupTime(LocalDateTime actualWakeupTime) {
        this.actualWakeupTime = actualWakeupTime;
    }

}

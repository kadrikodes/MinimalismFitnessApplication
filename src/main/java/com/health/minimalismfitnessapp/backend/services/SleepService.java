package com.health.minimalismfitnessapp.backend.services;

import com.health.minimalismfitnessapp.backend.dataaccess.ISleepRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.entities.SleepData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SleepService {
    ISleepRepository sleepRepository;
    IUserRepository userRepository;
    @Autowired
    public SleepService(IUserRepository userRepository, ISleepRepository sleepRepository) {
        this.userRepository = userRepository;
        this.sleepRepository = sleepRepository;
    }

    public List<UserData> findAll() {
        return this.userRepository.findAll();
    }

    public List<SleepData> getAllSleepData() {
        return sleepRepository.findAll();
    }

    public SleepData getSleepDataById(long sleepId){
        Optional<SleepData> sleepTracker = sleepRepository.findById(sleepId);
        return sleepTracker.orElse(null);
    }

    public List<SleepData> getSleepRecordByName(String name) {
        return sleepRepository.findSleepDataByUserDataName(name);
    }

    public SleepData addSleepRecord(SleepData sleepData) {
        UserData userData = sleepData.getUser();
        if(userData.getId() == null) {
            userData = userRepository.save(userData);
            sleepData.setUser(userData);
        }
        return sleepRepository.save(sleepData);
    }

    public SleepData updateSleepRecord(Long sleepDataId, SleepData updatedSleepData) {
        SleepData existingSleepData = sleepRepository.findById(sleepDataId)
                .orElseThrow(() -> new EntityNotFoundException("Walking data not found"));
        existingSleepData.setActualBedtime(updatedSleepData.getActualBedtime());
        existingSleepData.setActualWakeupTime(updatedSleepData.getActualWakeupTime());
        existingSleepData.setTargetBedtime(updatedSleepData.getTargetBedtime());
        existingSleepData.setTargetWakeUpTime(updatedSleepData.getTargetWakeUpTime());

        return sleepRepository.save(existingSleepData);
    }



    public void deleteSleepRecord(Long sleepDataId) {
        if (sleepRepository.existsById(sleepDataId)) {
            sleepRepository.deleteById(sleepDataId);
        }
        else {
            throw new IllegalArgumentException("Sleep record not found with ID:" + sleepDataId);
        }
    }

    public Duration targetSleepDuration(SleepData sleepData) {
        if (sleepData.getTargetBedtime() != null && sleepData.getTargetWakeUpTime() != null) {
            Duration hoursSleptOnTheSameDay = Duration.between(sleepData.getTargetBedtime(), sleepData.getTargetWakeUpTime());

            // Check if sleep crosses midnight
            if (sleepData.getTargetBedtime().isAfter(sleepData.getTargetWakeUpTime())) {
                // Calculate hours slept on the next day
                hoursSleptOnTheSameDay = hoursSleptOnTheSameDay.plus(Duration.ofHours(24));
            }

            return hoursSleptOnTheSameDay;
        } else {
            throw new IllegalArgumentException("Please provide both target bedtime and wake-up time");
        }
    }
}
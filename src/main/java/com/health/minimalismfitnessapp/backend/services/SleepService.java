package com.health.minimalismfitnessapp.backend.services;

import com.health.minimalismfitnessapp.backend.dataaccess.ISleepRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.entities.SleepData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;

import java.util.List;
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
        if (updatedSleepData == null) {
            throw new IllegalArgumentException("Updated sleep data cannot be null");
        }

        Optional<SleepData> existingSleepDataOptional = sleepRepository.findById(sleepDataId);

        if (existingSleepDataOptional.isPresent()) {
            SleepData existingSleepData = existingSleepDataOptional.get();

            // Perform basic data validation if needed
            if (isValidSleepData(updatedSleepData)) {
                // Check if user data needs to be updated
                UserData userData = updatedSleepData.getUser();
                if (userData != null && userData.getId() == null) {
                    userData = userRepository.save(userData);
                    existingSleepData.setUser(userData);
                }

                existingSleepData.setTargetBedtime(updatedSleepData.getTargetBedtime());
                existingSleepData.setTargetWakeUpTime(updatedSleepData.getTargetWakeUpTime());
                existingSleepData.setActualBedtime(updatedSleepData.getActualBedtime());
                existingSleepData.setActualWakeupTime(updatedSleepData.getActualWakeupTime());

                return sleepRepository.save(existingSleepData);
            } else {
                throw new IllegalArgumentException("Invalid sleep data provided");
            }
        } else {
            throw new IllegalArgumentException("Sleep record not found with ID: " + sleepDataId);
        }
    }


    private boolean isValidSleepData(SleepData sleepData) {
        // Perform validation checks here (e.g., check if target bedtime is before target wakeup time)
        // Return true if the data is valid, false otherwise

        // Example of basic validation: Check if target bedtime is before target wakeup time
        return sleepData.getTargetBedtime() != null && sleepData.getTargetWakeUpTime() != null &&
                sleepData.getTargetBedtime().isBefore(sleepData.getTargetWakeUpTime());
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
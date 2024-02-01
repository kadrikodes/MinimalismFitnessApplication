package com.health.minimalismfitnessapp.backend;

import com.health.minimalismfitnessapp.backend.dataaccess.*;
import com.health.minimalismfitnessapp.backend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Component
public class Populator {
    INutritionRepository iNutritionRepository;
    IPushUpRepository iPushUpRepository;
    ISleepRepository iSleepRepository;
    IUserRepository iUserRepository;
    IWalkingRepository iWalkingRepository;
    IActivityRepository iActivityRepository;

    @Autowired
    public Populator(IActivityRepository iActivityRepository, INutritionRepository iNutritionRepository, IPushUpRepository iPushUpRepository, ISleepRepository iSleepRepository, IUserRepository iUserRepository, IWalkingRepository iWalkingRepository) {
        this.iActivityRepository = iActivityRepository;
        this.iNutritionRepository = iNutritionRepository;
        this.iPushUpRepository = iPushUpRepository;
        this.iSleepRepository = iSleepRepository;
        this.iUserRepository = iUserRepository;
        this.iWalkingRepository = iWalkingRepository;
    }

    public void populate(){
        populateUserData();
        populateActivityData();
        populateNutritionData();
        populatePushUpData();
        populateWalkingData();
        populateSleepData();
    }

    public void populateNutritionData() {
        // Create user data
        Optional<UserData> optionalUserData1 = iUserRepository.findById(1L); // Example user ID
        Optional<UserData> optionalUserData2 = iUserRepository.findById(2L); // Example user ID
        if (optionalUserData1.isPresent() && optionalUserData2.isPresent()) {
            UserData userData1 = optionalUserData1.get();
            UserData userData2 = optionalUserData2.get();
            // Create nutrition data
            NutritionData nutritionData1 = new NutritionData("Pizza", 500, 40, 30, 30, "Lunch", userData1);
            iNutritionRepository.save(nutritionData1);
            NutritionData nutritionData2 = new NutritionData("Burger", 600, 60, 20, 20, "Dinner", userData2);
            iNutritionRepository.save(nutritionData2);
        }
    }

    public void populatePushUpData() {
        // Create user data
        Optional<UserData> optionalUserData1 = iUserRepository.findById(1L); // Example user ID
        Optional<UserData> optionalUserData2 = iUserRepository.findById(2L); // Example user ID
        if (optionalUserData1.isPresent() && optionalUserData2.isPresent()) {
            UserData userData1 = optionalUserData1.get();
            UserData userData2 = optionalUserData2.get();
            // Create activity data
            Optional<ActivityData> optionalActivityData = iActivityRepository.findById(1L); // Example activity ID
            if (optionalActivityData.isPresent()) {
                ActivityData activityData = optionalActivityData.get();
                // Create push up data
                PushUpData pushUpData1 = new PushUpData(20, 25, 5, 50, userData1, activityData);
                iPushUpRepository.save(pushUpData1);
                PushUpData pushUpData2 = new PushUpData(25, 30, 7, 70, userData2, activityData);
                iPushUpRepository.save(pushUpData2);
                PushUpData pushUpData3 = new PushUpData(27, 30, 10, 90, userData2, activityData);
                iPushUpRepository.save(pushUpData3);
            }
        }
    }

    public void populateSleepData() {
        // Create user data
        Optional<UserData> optionalUserData1 = iUserRepository.findById(1L); // Example user ID
        Optional<UserData> optionalUserData2 = iUserRepository.findById(2L); // Example user ID
        if (optionalUserData1.isPresent() && optionalUserData2.isPresent()) {
            UserData userData1 = optionalUserData1.get();
            UserData userData2 = optionalUserData2.get();
            // Create sleep data
            SleepData sleepData1 = new SleepData(LocalTime.of(22, 00), LocalTime.of(07, 00), LocalTime.of(22, 30), LocalTime.of(07, 30, 15), userData1);
            iSleepRepository.save(sleepData1);
            SleepData sleepData2 = new SleepData(LocalTime.of(22, 00), LocalTime.of(07, 00), LocalTime.of(22, 30), LocalTime.of(07, 30, 15), userData1);
            iSleepRepository.save(sleepData2);
            SleepData sleepData3 = new SleepData(LocalTime.of(22, 00), LocalTime.of(07, 00), LocalTime.of(22, 30), LocalTime.of(07, 30, 15), userData2);
            iSleepRepository.save(sleepData3);
        }
    }

    public void populateUserData() {
        // Create user data
        UserData userData1 = new UserData("Rais", 180, 85, LocalDate.of(2000, 1, 1), "MALE");
        iUserRepository.save(userData1);
        UserData userData2 = new UserData("Divin", 160, 68, LocalDate.of(1999, 1, 1), "MALE");
        iUserRepository.save(userData2);
    }

    public void populateWalkingData() {
        // Create user data
        Optional<UserData> optionalUserData1 = iUserRepository.findById(1L); // Example user ID
        Optional<UserData> optionalUserData2 = iUserRepository.findById(2L); // Example user ID
        if (optionalUserData1.isPresent() && optionalUserData2.isPresent()) {
            UserData userData1 = optionalUserData1.get();
            UserData userData2 = optionalUserData2.get();
            // Create walking data
            WalkingData walkingData1 = new WalkingData(1, 10, 100, 60, 5, LocalDateTime.of(2023, 11, 10, 12, 30), userData1);
            iWalkingRepository.save(walkingData1);
            WalkingData walkingData2 = new WalkingData(10, 20, 120, 70, 10, LocalDateTime.of(2021, 10, 15, 12, 30), userData2);
            iWalkingRepository.save(walkingData2);
            WalkingData walkingData3 = new WalkingData(1, 30, 150, 80, 15, LocalDateTime.of(2023, 11, 10, 12, 30), userData2);
            iWalkingRepository.save(walkingData3);
        }
    }

    public void populateActivityData() {
        // Create activity data
        ActivityData activityData1 = new ActivityData("PushUps", 50);
        iActivityRepository.save(activityData1);
        ActivityData activityData2 = new ActivityData("Walking", 10);
        iActivityRepository.save(activityData2);
        ActivityData activityData3 = new ActivityData("PushUps", 60);
        iActivityRepository.save(activityData3);
        ActivityData activityData4 = new ActivityData("Walking", 20);
        iActivityRepository.save(activityData4);
    }
}

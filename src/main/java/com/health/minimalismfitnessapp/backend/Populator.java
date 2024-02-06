package com.health.minimalismfitnessapp.backend;

import com.health.minimalismfitnessapp.backend.dataaccess.*;
import com.health.minimalismfitnessapp.backend.entities.NutritionData;
import com.health.minimalismfitnessapp.backend.entities.PushUpData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserGender;
import com.health.minimalismfitnessapp.backend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        Optional<UserData> optionalUserData1 = iUserRepository.findById(1L);
        Optional<UserData> optionalUserData2 = iUserRepository.findById(2L);
        Optional<UserData> optionalUserData3 = iUserRepository.findById(3L);
        Optional<UserData> optionalUserData4 = iUserRepository.findById(4L);
        Optional<UserData> optionalUserData5 = iUserRepository.findById(5L);


        if (optionalUserData1.isPresent() && optionalUserData2.isPresent()) {
            UserData userData1 = optionalUserData1.get();
            UserData userData2 = optionalUserData2.get();
            UserData userData3 = optionalUserData3.get();
            UserData userData4 = optionalUserData4.get();
            UserData userData5 = optionalUserData5.get();

            // Create nutrition data
            NutritionData nutritionData = new NutritionData("Pizza", 500, 40, 30, 30, "Lunch", userData1);
            iNutritionRepository.save(nutritionData);
            nutritionData = new NutritionData("Chicken", 500, 40, 30, 30, "Dinner", userData1);
            iNutritionRepository.save(nutritionData);
            nutritionData = new NutritionData("Burger", 600, 60, 20, 20, "Dinner", userData2);
            iNutritionRepository.save(nutritionData);
            nutritionData = new NutritionData("Oatmeal", 300, 20, 40, 10, "Breakfast", userData3);
            iNutritionRepository.save(nutritionData);
            nutritionData = new NutritionData("Salad", 350, 25, 30, 15, "Lunch", userData4);
            iNutritionRepository.save(nutritionData);
            nutritionData = new NutritionData("Hummus", 350, 25, 30, 15, "Breakfast", userData5);
            iNutritionRepository.save(nutritionData);
        }
    }

    public void populatePushUpData() {
        // Create user data
        Optional<UserData> optionalUserData1 = iUserRepository.findById(1L);
        Optional<UserData> optionalUserData2 = iUserRepository.findById(2L);
        Optional<UserData> optionalUserData3 = iUserRepository.findById(3L);
        Optional<UserData> optionalUserData4 = iUserRepository.findById(4L);
        Optional<UserData> optionalUserData5 = iUserRepository.findById(5L);

        if (optionalUserData1.isPresent() && optionalUserData2.isPresent()) {
            UserData userData1 = optionalUserData1.get();
            UserData userData2 = optionalUserData2.get();
            UserData userData3 = optionalUserData3.get();
            UserData userData4 = optionalUserData4.get();
            UserData userData5 = optionalUserData5.get();

            // Create activity data
            Optional<ActivityData> optionalActivityData = iActivityRepository.findById(1L);

            if (optionalActivityData.isPresent()) {
                ActivityData activityData = optionalActivityData.get();

                // Create push up data
                PushUpData pushUpData = new PushUpData(20, 25, 5, 50, userData1, activityData);
                iPushUpRepository.save(pushUpData);
                pushUpData = new PushUpData(25, 30, 7, 70, userData2, activityData);
                iPushUpRepository.save(pushUpData);
                pushUpData = new PushUpData(27, 30, 10, 90, userData4, activityData);
                iPushUpRepository.save(pushUpData);
                pushUpData = new PushUpData(27, 30, 10, 90, userData5, activityData);
                iPushUpRepository.save(pushUpData);
                pushUpData = new PushUpData(50, 70, 30, 190, userData3, activityData);
                iPushUpRepository.save(pushUpData);
            }
        }
    }

    public void populateSleepData() {
        // Create user data
        Optional<UserData> optionalUserData1 = iUserRepository.findById(1L);
        Optional<UserData> optionalUserData2 = iUserRepository.findById(2L);
        Optional<UserData> optionalUserData3 = iUserRepository.findById(3L);
        Optional<UserData> optionalUserData4 = iUserRepository.findById(4L);
        Optional<UserData> optionalUserData5 = iUserRepository.findById(5L);


        if (optionalUserData1.isPresent() && optionalUserData2.isPresent()) {
            UserData userData1 = optionalUserData1.get();
            UserData userData2 = optionalUserData2.get();
            UserData userData3 = optionalUserData3.get();
            UserData userData4 = optionalUserData4.get();
            UserData userData5 = optionalUserData5.get();

            // Create sleep data
            SleepData sleepData = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData1);
            iSleepRepository.save(sleepData);
            sleepData = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData2);
            iSleepRepository.save(sleepData);
            sleepData = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData3);
            iSleepRepository.save(sleepData);
            sleepData = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData4);
            iSleepRepository.save(sleepData);
            sleepData = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData5);
            iSleepRepository.save(sleepData);

        }
    }

    public void populateUserData() {
        // Create user data
        UserData userData = new UserData("Rais", 180, 85, LocalDate.of(2000, 1, 1), UserGender.MALE);
        iUserRepository.save(userData);
        userData = new UserData("Divin", 160, 68, LocalDate.of(1994, 1, 1), UserGender.MALE);
        iUserRepository.save(userData);
        userData = new UserData("Sam", 185, 75, LocalDate.of(1997, 2, 1), UserGender.MALE);
        iUserRepository.save(userData);
        userData = new UserData("Esra", 170, 68, LocalDate.of(1999, 6, 6), UserGender.FEMALE);
        iUserRepository.save(userData);
        userData = new UserData("Kadri", 188, 68, LocalDate.of(1999, 5, 10), UserGender.MALE);
        iUserRepository.save(userData);
    }

    public void populateWalkingData() {
        // Create user data
        Optional<UserData> optionalUserData1 = iUserRepository.findById(1L);
        Optional<UserData> optionalUserData2 = iUserRepository.findById(2L);
        Optional<UserData> optionalUserData3 = iUserRepository.findById(3L);
        Optional<UserData> optionalUserData4 = iUserRepository.findById(4L);
        Optional<UserData> optionalUserData5 = iUserRepository.findById(5L);
        if (optionalUserData1.isPresent() && optionalUserData2.isPresent()) {
            UserData userData1 = optionalUserData1.get();
            UserData userData2 = optionalUserData2.get();
            UserData userData3 = optionalUserData3.get();
            UserData userData4 = optionalUserData4.get();
            UserData userData5 = optionalUserData5.get();

            // Create activity data
            Optional<ActivityData> optionalActivityData = iActivityRepository.findById(2L); // Example activity ID

            if (optionalActivityData.isPresent() && optionalActivityData.isPresent()) {
                ActivityData activityData = optionalActivityData.get();
                // Create walking data
                WalkingData walkingData = new WalkingData(1, 10, 100, 60, 5, LocalDateTime.of(2023, 11, 10, 12, 30), userData1, activityData);
                iWalkingRepository.save(walkingData);
                walkingData = new WalkingData(10, 20, 120, 70, 10, LocalDateTime.of(2021, 10, 15, 12, 30), userData2, activityData);
                iWalkingRepository.save(walkingData);
                walkingData = new WalkingData(1, 30, 150, 80, 15, LocalDateTime.of(2023, 11, 10, 12, 30), userData3, activityData);
                iWalkingRepository.save(walkingData);
                walkingData = new WalkingData(1, 30, 150, 80, 15, LocalDateTime.of(2023, 11, 10, 12, 30), userData4, activityData);
                iWalkingRepository.save(walkingData);
                walkingData = new WalkingData(1, 30, 150, 80, 15, LocalDateTime.of(2023, 11, 10, 12, 30), userData5, activityData);
                iWalkingRepository.save(walkingData);
                walkingData = new WalkingData(1, 30, 150, 80, 15, LocalDateTime.of(2023, 11, 10, 12, 30), userData1, activityData);
                iWalkingRepository.save(walkingData);
            }
        }
    }


    public void populateActivityData() {
        // Create activity data
        ActivityData activityData1 = new ActivityData("PushUps");
        iActivityRepository.save(activityData1);
        ActivityData activityData2 = new ActivityData("Walking");
        iActivityRepository.save(activityData2);
        ActivityData activityData3 = new ActivityData("Skiing");
        iActivityRepository.save(activityData3);
        ActivityData activityData4 = new ActivityData("Running");
        iActivityRepository.save(activityData4);
    }
}

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
    UserData userData;
    ActivityData activityData;

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
        populateActivityData();
        populateUserData();
        populatePushUpData();
        populateNutritionData();
        populateWalkingData();

    }

    public void populateNutritionData() {
            NutritionData nutritionData1 = new NutritionData("Pizza", 500, 40, 30, 30, "Lunch", userData);
            iNutritionRepository.save(nutritionData1);

            NutritionData nutritionData2 = new NutritionData("Burger", 600, 60, 20, 20, "Dinner", userData);
            iNutritionRepository.save(nutritionData2);
    }



    public void populatePushUpData() {
        PushUpData pushUpData = new PushUpData(20,25,5,50,userData, activityData);
        iPushUpRepository.save(pushUpData);
        pushUpData = new PushUpData(25,30,7,70,userData, activityData);
        iPushUpRepository.save(pushUpData);
        pushUpData = new PushUpData(27,30,10,90,userData, activityData);
        iPushUpRepository.save(pushUpData);

    }

    public void populateSleepData() {
        SleepData sleepData = new SleepData(LocalTime.of(22,00), LocalTime.of(07,00), LocalTime.of(22, 30), LocalTime.of(07,30, 15), userData);
        iSleepRepository.save(sleepData);
        sleepData = new SleepData(LocalTime.of(22,00), LocalTime.of(07,00), LocalTime.of(22, 30), LocalTime.of(07,30, 15), userData);
        iSleepRepository.save(sleepData);
        sleepData = new SleepData(LocalTime.of(22,00), LocalTime.of(07,00), LocalTime.of(22, 30), LocalTime.of(07,30, 15), userData);
        iSleepRepository.save(sleepData);

    }

    public void populateUserData() {
        UserData userData = new UserData("Rais", 180, 85, LocalDate.of(2000,1,1), "MALE");
        iUserRepository.save(userData);
        userData = new UserData("Divin", 160, 68, LocalDate.of(1999,1,1), "MALE");
        iUserRepository.save(userData);

    }

    public void populateWalkingData() {
        WalkingData walkingData = new WalkingData(1, 10, 100, 60, 5, LocalDateTime.of(2023, 11, 10, 12, 30), userData);
        iWalkingRepository.save(walkingData);
        walkingData = new WalkingData(10, 20, 120, 70, 10, LocalDateTime.of(2021, 10, 15, 12, 30), userData);
        iWalkingRepository.save(walkingData);
        walkingData = new WalkingData(1, 30, 150, 80, 15, LocalDateTime.of(2023, 11, 10, 12, 30), userData);
        iWalkingRepository.save(walkingData);

    }

    public void populateActivityData() {
        ActivityData activityData = new ActivityData("PushUps", 50);
        iActivityRepository.save(activityData);
        activityData = new ActivityData("Walking", 10);
        iActivityRepository.save(activityData);
        activityData = new ActivityData("PushUps", 60);
        iActivityRepository.save(activityData);
        activityData = new ActivityData("Walking", 20);
        iActivityRepository.save(activityData);

    }

}

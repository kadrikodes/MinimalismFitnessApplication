package com.health.MinimalismFitnessApp;

import com.health.MinimalismFitnessApp.dataaccess.*;
import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Populator {
    INutritionRepository iNutritionRepository;
    IPushUpRepository iPushUpRepository;
    ISleepRepository iSleepRepository;
    IUserRepository iUserRepository;
    IWalkingRepository iWalkingRepository;

    @Autowired
    public Populator(INutritionRepository iNutritionRepository, IPushUpRepository iPushUpRepository, ISleepRepository iSleepRepository, IUserRepository iUserRepository, IWalkingRepository iWalkingRepository) {
        this.iNutritionRepository = iNutritionRepository;
        this.iPushUpRepository = iPushUpRepository;
        this.iSleepRepository = iSleepRepository;
        this.iUserRepository = iUserRepository;
        this.iWalkingRepository = iWalkingRepository;
    }

    public void populateNutritionData() {
        NutritionData nutritionData = new NutritionData(1000L, "Pizza", 500, 40, 30, 30, "Lunch", new UserData("Rais", 1L, 180, 85, LocalDate.of(2000,1,1), "MALE"));
        iNutritionRepository.save(nutritionData);
        nutritionData = new NutritionData(2000L, "Burger", 600, 60, 20, 20, "Dinner", new UserData("Divin", 2L, 160, 68, LocalDate.of(1999,1,1), "MALE"));
        iNutritionRepository.save(nutritionData);

    }

    public void populatePushUpData() {

    }

    public void populateSleepData() {

    }

    public void populateUserData() {

    }

    public void populateWalkingData() {

    }

}

package com.health.MinimalismFitnessApp;

import com.health.MinimalismFitnessApp.dataaccess.*;
import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.entities.PushUpData;
import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

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

    public void populate(){
        UserData userData = new UserData("Rais", 180, 85, LocalDate.of(2000,1,1), "MALE");
        iUserRepository.save(userData);
        userData = new UserData("Divin", 160, 68, LocalDate.of(1999,1,1), "MALE");
        iUserRepository.save(userData);
        PushUpData pushUpData = new PushUpData(20,25,5,50,userData);
        iPushUpRepository.save(pushUpData);
    }

    public void populateNutritionData() {
        Optional<UserData> user1Optional = iUserRepository.findById(1L);
        Optional<UserData> user2Optional = iUserRepository.findById(2L);

        System.out.println("User 1 exists: " + user1Optional.isPresent());
        System.out.println("User 2 exists: " + user2Optional.isPresent());

        if (user1Optional.isPresent() && user2Optional.isPresent()) {
            UserData user1 = user1Optional.get();
            UserData user2 = user2Optional.get();

            NutritionData nutritionData1 = new NutritionData("Pizza", 500, 40, 30, 30, "Lunch", user1);
            iNutritionRepository.save(nutritionData1);

            NutritionData nutritionData2 = new NutritionData("Burger", 600, 60, 20, 20, "Dinner", user2);
            iNutritionRepository.save(nutritionData2);
        } else {
            System.out.println("User with ID 1 or 2 not found");
            // Add the following logs to see the content of the repository
            iUserRepository.findAll().forEach(user -> System.out.println("User in repository: " + user));
        }
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

package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.INutritionRepository;
import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NutritionService {
    INutritionRepository nutritionRepository;
    IUserRepository userRepository;

    @Autowired
    public NutritionService(INutritionRepository nutritionRepository, IUserRepository userRepository) {
        this.nutritionRepository = nutritionRepository;
        this.userRepository = userRepository;
    }


    public List<NutritionData> findAll(){ return nutritionRepository.findAll();}

    public NutritionData getNutritionID(long nutritionId){
        Optional<NutritionData> nutritionData = nutritionRepository.findById(nutritionId);
        return nutritionData.orElse(null);
    }

    public List<NutritionData> getNutritionByName(String name){ return nutritionRepository.findNutritionDataByUserDataName(name);}

    public NutritionData addNutritionData(NutritionData nutritionData){
        String name = nutritionData.getUser().getName();
        Optional<UserData> prospectiveUser = userRepository.findUserDataByName(name);
        if(prospectiveUser.isEmpty()){
            UserData userData = userRepository.save(nutritionData.getUser());
            nutritionData.setUser(userData);
        } else{
            nutritionData.setUser(prospectiveUser.get());
        }

        return nutritionRepository.save(nutritionData);
    }

    public void deleteNutritionData(long nutritionID){
        if (nutritionRepository.existsById(nutritionID)){
            nutritionRepository.deleteById(nutritionID);
        }else{
            throw new IllegalArgumentException("Nutrition data with ID " + nutritionID + " not found");
        }
    }

    public NutritionData updateNutritionData(long nutritionID, NutritionData nutritionData) {
        NutritionData nutritionDataUpdate = nutritionRepository.findById(nutritionID)
                .orElseThrow(() -> new NoSuchElementException("NutritionData not found with ID: " + nutritionID));

        nutritionDataUpdate.setFoodName(nutritionData.getFoodName());
        nutritionDataUpdate.setCalories(nutritionData.getCalories());
        nutritionDataUpdate.setProtein(nutritionData.getProtein());
        nutritionDataUpdate.setCarbohydrates(nutritionData.getCarbohydrates());
        nutritionDataUpdate.setFats(nutritionData.getFats());
        nutritionDataUpdate.setMealType(nutritionData.getMealType());
        nutritionDataUpdate.setUser(nutritionData.getUser());

        nutritionRepository.save(nutritionDataUpdate);
        return nutritionDataUpdate;
    }
}

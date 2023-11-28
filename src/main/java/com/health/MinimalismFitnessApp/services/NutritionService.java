package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.INutritionRepository;
import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutritionService {
    INutritionRepository nutritionRepository;
    IUserRepository userRepository;

    @Autowired
    public NutritionService(INutritionRepository nutritionRepository) {this.nutritionRepository = nutritionRepository; }

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
        nutritionRepository.deleteById(nutritionID);
    }

    public void updateNutritionData(long nutritionID, NutritionData nutritionData) {
        NutritionData oldNutritionData = nutritionRepository.findById(nutritionID).orElseThrow();

        oldNutritionData.setFoodName(nutritionData.getFoodName());
        oldNutritionData.setCalories(nutritionData.getCalories());
        oldNutritionData.setProtein(nutritionData.getProtein());
        oldNutritionData.setCarbohydrates(nutritionData.getCarbohydrates());
        oldNutritionData.setFats(nutritionData.getFats());
        oldNutritionData.setMealType(nutritionData.getMealType());
        oldNutritionData.setUser(nutritionData.getUser());

        nutritionRepository.save(oldNutritionData);
    }
}

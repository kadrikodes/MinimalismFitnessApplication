package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.INutritionRepository;
import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

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

    public List<NutritionData> getNutritionByName(String name){ return nutritionRepository.findNutritionDataByName(name);}

    public NutritionData addNutritionData(NutritionData nutritionData){
        String name = nutritionData.getUser().getName();
        Optional<UserData> prospectiveUser = userRepository.findByName(name); //Esra TODO
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

    public boolean checkNutritionDataExistsByID(long nutritionID){
        return nutritionRepository.existsById(nutritionID);
    }
}

package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.INutritionRepository;
import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.NutritionData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class NutritionService {
    INutritionRepository nutritionRepository;
    IUserRepository userRepository;

    @Autowired
    public NutritionService(INutritionRepository nutritionRepository) {this.nutritionRepository = nutritionRepository; }

    public List<NutritionData> findAll(){ return nutritionRepository.findAll();}

    public NutritionData getNutritionID(long nutritionId){
        Optional<NutritionData> nutritionTracker = nutritionRepository.findById(nutritionId);
        return nutritionTracker.orElse(null);
    }

    public List<NutritionData> getNutritionByName(String name){ return nutritionRepository.findNutritionDataByName(name);}

    public NutritionData addNutritionData(NutritionData nutritionData){
        return null;
    }
}

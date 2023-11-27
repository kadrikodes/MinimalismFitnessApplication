package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.INutritionRepository;
import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.NutritionTracker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class NutritionService {
    INutritionRepository nutritionRepository;
    IUserRepository userRepository;

    @Autowired
    public NutritionService(INutritionRepository nutritionRepository) {this.nutritionRepository = nutritionRepository; }

    public List<NutritionTracker> findAll(){ return nutritionRepository.findAll();}

    public NutritionTracker getNutritionID(long nutritionId){
        Optional<NutritionTracker> nutritionTracker = nutritionRepository.findById(nutritionId);
        return nutritionTracker.orElse(null);
    }

    public List<NutritionTracker> getNutritionByName(String name){ return nutritionRepository.findNutritionDataByName(name);}






}

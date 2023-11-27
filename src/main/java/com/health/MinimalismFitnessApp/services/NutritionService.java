package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.INutritionRepository;
import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.NutritionTracker;

import java.util.List;
import java.util.Optional;

public class NutritionService {
    INutritionRepository nutritionRepository;
    IUserRepository userRepository;

    public List<NutritionTracker> findAll(){ return nutritionRepository.findAll();}

    public NutritionTracker getNutritionID(long nutritionId){
        Optional<NutritionTracker> nutritionTracker = nutritionRepository.findById(nutritionId);
        return nutritionTracker.orElse(null);
    }

    public List<NutritionTracker> getNutritionByName(String name){ return nutritionRepository.findNutritionDataByName(name);}






}

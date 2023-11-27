package com.health.MinimalismFitnessApp.dataaccess;

import com.health.MinimalismFitnessApp.entities.NutritionTracker;

import java.util.List;

public interface INutritionRepository {
    List<NutritionTracker> findNutritionDataByName(String name);
}

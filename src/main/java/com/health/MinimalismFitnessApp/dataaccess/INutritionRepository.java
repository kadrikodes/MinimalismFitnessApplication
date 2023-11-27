package com.health.MinimalismFitnessApp.dataaccess;

import com.health.MinimalismFitnessApp.entities.NutritionTracker;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface INutritionRepository extends ListCrudRepository<NutritionTracker, Long> {
    List<NutritionTracker> findNutritionDataByName(String name);
}

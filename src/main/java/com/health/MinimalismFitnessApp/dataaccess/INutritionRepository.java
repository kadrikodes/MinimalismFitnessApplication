package com.health.MinimalismFitnessApp.dataaccess;

import com.health.MinimalismFitnessApp.entities.NutritionData;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface INutritionRepository extends ListCrudRepository<NutritionData, Long> {
    List<NutritionData> findNutritionDataByName(String name);
}

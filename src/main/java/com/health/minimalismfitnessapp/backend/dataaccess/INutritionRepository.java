package com.health.minimalismfitnessapp.backend.dataaccess;

import com.health.minimalismfitnessapp.backend.entities.NutritionData;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INutritionRepository extends ListCrudRepository<NutritionData, Long> {
    List<NutritionData> findNutritionDataByUserDataName(String name);
}

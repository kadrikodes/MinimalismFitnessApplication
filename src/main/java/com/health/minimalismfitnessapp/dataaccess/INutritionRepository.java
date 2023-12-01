package com.health.minimalismfitnessapp.dataaccess;

import com.health.minimalismfitnessapp.entities.NutritionData;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INutritionRepository extends ListCrudRepository<NutritionData, Long> {
    List<NutritionData> findNutritionDataByUserDataName(String name);
}

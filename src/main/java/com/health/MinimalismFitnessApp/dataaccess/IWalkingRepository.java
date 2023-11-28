package com.health.MinimalismFitnessApp.dataaccess;

import com.health.MinimalismFitnessApp.entities.WalkingData;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IWalkingRepository extends ListCrudRepository<WalkingData, Long> {
    List<WalkingData> findWalkingTrackerByUserName(String name);
}

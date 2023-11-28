package com.health.MinimalismFitnessApp.dataaccess;

import com.health.MinimalismFitnessApp.entities.WalkingTracker;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IWalkingRepository extends ListCrudRepository<WalkingTracker, Long> {
    List<WalkingTracker> findWalkingTrackerByUserName(String name);
}

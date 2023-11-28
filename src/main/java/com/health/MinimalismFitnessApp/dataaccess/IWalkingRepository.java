package com.health.MinimalismFitnessApp.dataaccess;

import com.health.MinimalismFitnessApp.entities.WalkingData;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IWalkingRepository extends ListCrudRepository<WalkingData, Long> {
    List<WalkingData> findWalkingTrackerByUserName(String name);

    List<WalkingData> findByDateTimeAndDistance(LocalDateTime dateTime, double distance);

    List<WalkingData> findAllByOrderByDateAsc();
}

package com.health.minimalismfitnessapp.dataaccess;

import com.health.minimalismfitnessapp.entities.WalkingData;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IWalkingRepository extends ListCrudRepository<WalkingData, Long> {
    List<WalkingData> findWalkingDataByUserDataName(String name);

    List<WalkingData> findByDateTimeAndDistance(LocalDateTime dateTime, double distance);

}

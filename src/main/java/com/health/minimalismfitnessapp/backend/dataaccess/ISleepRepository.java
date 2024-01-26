package com.health.minimalismfitnessapp.backend.dataaccess;

import com.health.minimalismfitnessapp.backend.entities.SleepData;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISleepRepository extends ListCrudRepository<SleepData, Long> {
    List<SleepData> findSleepDataByUserDataName(String name);
}

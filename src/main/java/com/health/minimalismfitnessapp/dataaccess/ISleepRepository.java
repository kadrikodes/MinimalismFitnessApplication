package com.health.minimalismfitnessapp.dataaccess;

import com.health.minimalismfitnessapp.entities.SleepData;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISleepRepository extends ListCrudRepository<SleepData, Long> {
    List<SleepData> findSleepDataByUserDataName(String name);
}

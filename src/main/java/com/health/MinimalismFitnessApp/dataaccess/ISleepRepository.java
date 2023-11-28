package com.health.MinimalismFitnessApp.dataaccess;

import com.health.MinimalismFitnessApp.entities.SleepTracker;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISleepRepository extends ListCrudRepository<SleepTracker, Long> {
    List<SleepTracker> findSleepRecordByName(String name);
}

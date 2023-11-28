package com.health.MinimalismFitnessApp.dataaccess;


import com.health.MinimalismFitnessApp.entities.PushUpTracker;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IPushUpRepository extends ListCrudRepository<PushUpTracker, Long> {
    List<PushUpTracker> findPushUpTrackerByUserName(String name);
}

package com.health.MinimalismFitnessApp.dataaccess;


import com.health.MinimalismFitnessApp.entities.PushUpTracker;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPushUpRepository extends ListCrudRepository<PushUpTracker, Long> {
    List<PushUpTracker> findPushUpTrackerByUserName(String name);
}

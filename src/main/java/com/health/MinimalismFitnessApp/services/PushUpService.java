package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IPushUpRepository;
import com.health.MinimalismFitnessApp.entities.PushUpTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PushUpService {

    IPushUpRepository pushUpRepository;
    @Autowired
    public PushUpService(IPushUpRepository pushUpRepository) {
        this.pushUpRepository = pushUpRepository;
    }

    public List<PushUpTracker> findAll() {
        return pushUpRepository.findAll();
    }

    public PushUpTracker getPushUpTrackerById (long pushUpTrackerId) {
        Optional<PushUpTracker> pushUpTracker = pushUpRepository.findById(pushUpTrackerId);
        return pushUpTracker.orElse(null);
    }

    public List<PushUpTracker> getPushUpTrackerByUserName (String name) {
        return pushUpRepository.findPushUpTrackerByUserName(name);
    }

    public PushUpTracker addPushUpTrackerData(PushUpTracker pushUpTracker) {
        return pushUpTracker;
    }

}

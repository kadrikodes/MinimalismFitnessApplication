package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IPushUpRepository;
import com.health.MinimalismFitnessApp.entities.PushUpData;
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

    public List<PushUpData> findAll() {
        return pushUpRepository.findAll();
    }

    public PushUpData getPushUpTrackerById (long pushUpTrackerId) {
        Optional<PushUpData> pushUpTracker = pushUpRepository.findById(pushUpTrackerId);
        return pushUpTracker.orElse(null);
    }

    public List<PushUpData> getPushUpTrackerByUserName (String name) {
        return pushUpRepository.findPushUpTrackerByUserName(name);
    }

    public PushUpData addPushUpTrackerData(PushUpData pushUpData) {
        return pushUpData;
    }

}

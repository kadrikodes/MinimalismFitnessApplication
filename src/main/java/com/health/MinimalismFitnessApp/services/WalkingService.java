package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IWalkingRepository;
import com.health.MinimalismFitnessApp.entities.WalkingData;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalkingService {

    private IWalkingRepository walkingRepository;

    public List<WalkingData> findAll() {
        return walkingRepository.findAll();
    }

    public WalkingData getWalkingTrackerById(long walkingTrackerId) {
        Optional<WalkingData> walkingTracker = walkingRepository.findById(walkingTrackerId);
        return walkingTracker.orElse(null);
    }

    public java.util.List<WalkingData> getWalkingTrackerByUserName(String name) {
        return walkingRepository.findWalkingTrackerByUserName(name);
    }

    public WalkingData addWalkingTrackerData(WalkingData walkingData) {
        return walkingData;
    }
}

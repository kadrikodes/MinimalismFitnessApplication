package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IWalkingRepository;
import com.health.MinimalismFitnessApp.entities.WalkingTracker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalkingService {

    private IWalkingRepository walkingRepository;

    public List<WalkingTracker> findAll() {
        return walkingRepository.findAll();
    }

    public WalkingTracker getWalkingTrackerById(long walkingTrackerId) {
        Optional<WalkingTracker> walkingTracker = walkingRepository.findById(walkingTrackerId);
        return walkingTracker.orElse(null);
    }

    public java.util.List<WalkingTracker> getWalkingTrackerByUserName(String name) {
        return walkingRepository.findWalkingTrackerByUserName(name);
    }

    public WalkingTracker addWalkingTrackerData(WalkingTracker walkingTracker) {
        return walkingTracker;
    }
}

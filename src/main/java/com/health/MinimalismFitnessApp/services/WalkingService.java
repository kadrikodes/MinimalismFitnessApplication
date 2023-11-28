package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IWalkingRepository;
import com.health.MinimalismFitnessApp.entities.WalkingData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalkingService {

    private IWalkingRepository walkingRepository;

    public List<WalkingData> findAll() {
        return walkingRepository.findAll();
    }

    public WalkingData getWalkingDataById(long walkingTrackerId) {
        Optional<WalkingData> walkingTracker = walkingRepository.findById(walkingTrackerId);
        return walkingTracker.orElse(null);
    }

    public List<WalkingData> getWalkingDataByUserName(String name) {
        return walkingRepository.findWalkingDataByUserDataName(name);
    }

    public WalkingData addWalkingData(WalkingData walkingData) {
        return walkingData;
    }

    public List<WalkingData> searchEntriesByCriteria(LocalDateTime dateTime, double distance) {
        return walkingRepository.findByDateTimeAndDistance(dateTime, distance);
    }

    //public List<WalkingData> getEntriesSortedByData() {
    //    return walkingRepository.findAllByOrderByDateAsc();
   // }

    public WalkingData updateWalkingData(long id, WalkingData walkingData) {
        WalkingData existingWalkingData = walkingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Walking data not found"));

        existingWalkingData.setDistance(walkingData.getDistance());
        existingWalkingData.setSteps(walkingData.getSteps());
        existingWalkingData.setSpeed(walkingData.getSpeed());
        existingWalkingData.setCaloriesBurned(walkingData.getCaloriesBurned());

        return walkingRepository.save(existingWalkingData);
    }

    public void deleteWalkingTracker(long id) {
        WalkingData walkingData = walkingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Walking data not found"));

        walkingRepository.delete(walkingData);
    }
}

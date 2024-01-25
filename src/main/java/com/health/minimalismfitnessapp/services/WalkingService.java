package com.health.minimalismfitnessapp.services;

import com.health.minimalismfitnessapp.dataaccess.IWalkingRepository;
import com.health.minimalismfitnessapp.entities.WalkingData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalkingService {
    private final IWalkingRepository walkingRepository;

    @Autowired
    public WalkingService(IWalkingRepository walkingRepository) {
        this.walkingRepository = walkingRepository;
    }

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
        return walkingRepository.save(walkingData);
    }

    public List<WalkingData> searchEntriesByCriteria(LocalDateTime dateTime, double distance) {
        return walkingRepository.findByDateTimeAndDistance(dateTime, distance);
    }

    public WalkingData updateWalkingData(long walkingId, WalkingData walkingData) {
        WalkingData existingWalkingData = walkingRepository.findById(walkingId)
                .orElseThrow(() -> new EntityNotFoundException("Walking data not found"));

        existingWalkingData.setDistance(walkingData.getDistance());
        existingWalkingData.setSteps(walkingData.getSteps());
        existingWalkingData.setSpeed(walkingData.getSpeed());
        existingWalkingData.setCaloriesBurned(walkingData.getCaloriesBurned());

        return walkingRepository.save(existingWalkingData);
    }

    public void deleteWalkingTracker(long walkingId) {
        WalkingData walkingData = walkingRepository.findById(walkingId)
                .orElseThrow(() -> new EntityNotFoundException("Walking data not found"));

        walkingRepository.delete(walkingData);
    }

    public int calculateStepsToBurnCalories(double caloriesToBurn, double caloriesPerStep) {
        return (int) Math.ceil(caloriesToBurn / caloriesPerStep);
    }

    public double calculateWeightLoss(int stepsTaken, double caloriesPerStep, double caloriesPerKg) {
        double caloriesBurned = stepsTaken * caloriesPerStep;
        return caloriesBurned / caloriesPerKg;
    }
    public boolean hasAchievedDailyGoal(int stepsTaken, int dailyStepGoal) {
        return stepsTaken >= dailyStepGoal;
    }
    public boolean hasAchievedWeeklyGoal(int stepsTaken, int weeklyStepGoal) {
        return stepsTaken >= weeklyStepGoal;
    }
    public boolean hasAchievedMonthlyGoal(int stepsTaken, int monthlyStepGoal) {
        return stepsTaken >= monthlyStepGoal;
    }

    public double getCaloriesPerStep() {
        return 0.05;
    }

    public double getCaloriesPerKg() {
        return 7700;
    }

    public int getDailyStepGoal() { return 15000; }


    public int getWeeklyStepGoal() {
        return 105000;
    }

    public int getMonthlyStepGoal() {
        return 420000;
    }

    public double calculateTotalCaloriesBurned(long walkingId) {
        walkingRepository.findById(walkingId)
                .orElseThrow(() -> new EntityNotFoundException("Walking Id not found"));

        Optional<WalkingData> walkingDataList = walkingRepository.findById(walkingId);

        double totalCaloriesBurned = walkingDataList.stream()
                .mapToDouble(WalkingData::getCaloriesBurned)
                .sum();

        return totalCaloriesBurned;

    }
}

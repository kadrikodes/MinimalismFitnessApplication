package com.health.minimalismfitnessapp.backend.services;

import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IWalkingRepository;
import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalkingService {
    private final IWalkingRepository walkingRepository;
    IUserRepository userRepository;

    @Autowired
    public WalkingService(IWalkingRepository walkingRepository, IUserRepository userRepository) {
        this.walkingRepository = walkingRepository;
        this.userRepository = userRepository;
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
        Optional<UserData> prospectiveUser;
        Long userId = walkingData.getUserData().getId();
        if(userId == null || userId == 0){
            String name = walkingData.getUserData().getName();
            if(name == null || name.isEmpty()){
                throw new RuntimeException("Could not find user ID or name");
            }
            prospectiveUser = userRepository.findUserDataByName(name);
        } else {
            prospectiveUser = userRepository.findById(userId);
        }

        if(prospectiveUser.isEmpty()){
            throw new RuntimeException("Walking data needs a user");
        }

        walkingData.setUserData(prospectiveUser.get());
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

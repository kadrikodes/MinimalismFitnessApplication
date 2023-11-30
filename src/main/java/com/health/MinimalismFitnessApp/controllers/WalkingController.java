package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.WalkingData;
import com.health.MinimalismFitnessApp.services.WalkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("/walking")
public class WalkingController {

    WalkingService walkingService;
    Timer walkReminderTimer;

    @Autowired
    public WalkingController(WalkingService walkingService) {
        this.walkingService = walkingService;
    }

    @GetMapping("/allWalkingData")
    public List<WalkingData> getAllWalkingData() {
        return walkingService.findAll();
    }

    @GetMapping("/{walkingId}")
    public WalkingData getWalkingDataById(@PathVariable long walkingId) {
        WalkingData walkingData = walkingService.getWalkingDataById(walkingId);

        if (walkingData == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Walking data not found");

        return walkingData;
    }

    @GetMapping("/name/{name}")
    public List<WalkingData> getWalkingDataByUserName(@PathVariable String name) {
        return walkingService.getWalkingDataByUserName(name);
    }

    @PostMapping("/addWalkingData")
    public WalkingData addWalkingData(@RequestBody WalkingData walkingData) {
        return walkingService.addWalkingData(walkingData);
    }

    @GetMapping("/search")
    public List<WalkingData> searchWalkingData(@RequestParam(required = false)LocalDateTime dateTime, @RequestParam(required = false) double distance) {
        return walkingService.searchEntriesByCriteria(dateTime, distance);
    }

    @PutMapping("/update/{walkingId}")
    public WalkingData updateWalkingData(@PathVariable long walkingId, @RequestBody WalkingData walkingData) {
        return walkingService.updateWalkingData(walkingId, walkingData);
    }

    @DeleteMapping("/delete/{walkingId}")
    public ResponseEntity<String> deleteWalkingData(@PathVariable long walkingId) {
        walkingService.deleteWalkingTracker(walkingId);
        return ResponseEntity.ok("Walking data deleted successfully");
    }

    @GetMapping("/calculateStepsToBurnCalories")
    public int calculateStepsToBurnCalories(@RequestParam double caloriesToBurn) {
        double caloriesPerStep = walkingService.getCaloriesPerStep();
        return walkingService.calculateStepsToBurnCalories(caloriesToBurn, caloriesPerStep);
    }

    @GetMapping("/calculateWeightLoss")
    public double calculateWeightLoss(@RequestParam int stepsTaken) {
        double caloriesPerStep = walkingService.getCaloriesPerStep();
        double caloriesPerKg = walkingService.getCaloriesPerKg();
        return walkingService.calculateWeightLoss(stepsTaken, caloriesPerStep, caloriesPerKg);
    }

    @PostMapping("/scheduleWalkReminder")
    public ResponseEntity<String> scheduleWalkReminder(@RequestParam int hours, @RequestParam int minutes) {
        walkReminderTimer = new Timer();
        walkReminderTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("It's time for a walk!");
            }
        }, hours * 60 * 60 * 1000 + minutes * 60 * 1000);

        return ResponseEntity.ok("Walk reminder scheduled successfully");
    }

    @GetMapping("/hasAchievedDailyGoal")
    public boolean hasAchievedDailyGoal(@RequestParam int stepsTaken) {
        int dailyStepGoal = walkingService.getDailyStepGoal();
        return walkingService.hasAchievedDailyGoal(stepsTaken, dailyStepGoal);
    }
    @GetMapping("/hasAchievedWeeklyGoal")
    public boolean hasAchievedWeeklyGoal(@RequestParam int stepsTaken) {
        int weeklyStepGoal = walkingService.getWeeklyStepGoal();
        return walkingService.hasAchievedWeeklyGoal(stepsTaken, weeklyStepGoal);
    }
    @GetMapping("/hasAchievedMonthlyGoal")
    public boolean hasAchievedMonthlyGoal(@RequestParam int stepsTaken) {
        int monthlyStepGoal = walkingService.getMonthlyStepGoal();
        return walkingService.hasAchievedMonthlyGoal(stepsTaken, monthlyStepGoal);
    }

    @GetMapping("/calories/{walkingId}")
    public double getTotalCaloriesBurned(@PathVariable long walkingId) {
        return walkingService.calculateTotalCaloriesBurned(walkingId);
    }
}

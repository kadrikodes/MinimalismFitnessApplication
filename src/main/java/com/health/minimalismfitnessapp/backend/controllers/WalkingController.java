package com.health.minimalismfitnessapp.backend.controllers;

import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import com.health.minimalismfitnessapp.backend.services.WalkingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    final WalkingService walkingService;
    Timer walkReminderTimer;
    private static final Logger logger = LoggerFactory.getLogger(WalkingController.class);

    @Autowired
    public WalkingController(WalkingService walkingService) {
        this.walkingService = walkingService;
    }

    @GetMapping("/allWalkingData")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<WalkingData> getAllWalkingData() {
        return walkingService.findAll();
    }

    @GetMapping("/{walkingId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public WalkingData getWalkingDataById(@PathVariable long walkingId) {
        WalkingData walkingData = walkingService.getWalkingDataById(walkingId);
        if (walkingData == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Walking data not found");

        return walkingData;
    }

    @GetMapping("/name/{name}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<WalkingData> getWalkingDataByUserName(@PathVariable String name) {
        return walkingService.getWalkingDataByUserName(name);
    }

    @PostMapping("/addWalkingData")
    @CrossOrigin(origins = "http://localhost:3000")
    public WalkingData addWalkingData(@RequestBody WalkingData walkingData) {
        return walkingService.addWalkingData(walkingData);
    }

    @GetMapping("/search")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<WalkingData> searchWalkingData(@RequestParam(required = false)LocalDateTime dateTime, @RequestParam(required = false) double distance) {
        return walkingService.searchEntriesByCriteria(dateTime, distance);
    }

    @PutMapping("/update/{walkingId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public WalkingData updateWalkingData(@PathVariable long walkingId, @RequestBody WalkingData walkingData) {
        return walkingService.updateWalkingData(walkingId, walkingData);
    }

    @DeleteMapping("/delete/{walkingId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteWalkingData(@PathVariable long walkingId) {
        walkingService.deleteWalkingTracker(walkingId);
        return ResponseEntity.ok("Walking data deleted successfully");
    }

    @GetMapping("/calculateStepsToBurnCalories")
    @CrossOrigin(origins = "http://localhost:3000")
    public int calculateStepsToBurnCalories(@RequestParam double caloriesToBurn) {
        double caloriesPerStep = walkingService.getCaloriesPerStep();
        return walkingService.calculateStepsToBurnCalories(caloriesToBurn, caloriesPerStep);
    }

    @GetMapping("/calculateWeightLoss")
    @CrossOrigin(origins = "http://localhost:3000")
    public double calculateWeightLoss(@RequestParam int stepsTaken) {
        double caloriesPerStep = walkingService.getCaloriesPerStep();
        double caloriesPerKg = walkingService.getCaloriesPerKg();
        return walkingService.calculateWeightLoss(stepsTaken, caloriesPerStep, caloriesPerKg);
    }

    @PostMapping("/scheduleWalkReminder")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> scheduleWalkReminder(@RequestParam int hours, @RequestParam int minutes) {
        walkReminderTimer = new Timer();
        walkReminderTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info("It's time for a walk!");
            }
        }, (long) hours * 60 * 60 * 1000 + (long) minutes * 60 * 1000);
        return ResponseEntity.ok("Walk reminder scheduled successfully");
    }

    @GetMapping("/hasAchievedDailyGoal")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean hasAchievedDailyGoal(@RequestParam int stepsTaken) {
        int dailyStepGoal = walkingService.getDailyStepGoal();
        return walkingService.hasAchievedDailyGoal(stepsTaken, dailyStepGoal);
    }
    @GetMapping("/hasAchievedWeeklyGoal")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean hasAchievedWeeklyGoal(@RequestParam int stepsTaken) {
        int weeklyStepGoal = walkingService.getWeeklyStepGoal();
        return walkingService.hasAchievedWeeklyGoal(stepsTaken, weeklyStepGoal);

    }
    @GetMapping("/hasAchievedMonthlyGoal")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean hasAchievedMonthlyGoal(@RequestParam int stepsTaken) {
        int monthlyStepGoal = walkingService.getMonthlyStepGoal();
        return walkingService.hasAchievedMonthlyGoal(stepsTaken, monthlyStepGoal);
    }

    @GetMapping("/calories/{walkingId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public double getTotalCaloriesBurned(@PathVariable long walkingId) {
        return walkingService.calculateTotalCaloriesBurned(walkingId);
    }
}

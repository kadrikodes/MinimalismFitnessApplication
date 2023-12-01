package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.SleepData;
import com.health.MinimalismFitnessApp.services.SleepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/sleeptracker")    //Sleep tracker Endpoint
public class SleepController {
    private final SleepService sleepService;

    @Autowired
    public SleepController(SleepService sleepService) {
        this.sleepService = sleepService;
    }

    @GetMapping
    public List<SleepData> getAllSleepData() {
        return sleepService.getAllSleepData();
    }

    @GetMapping("/{sleepDataId}")
    public SleepData getSleepDataById(@PathVariable Long sleepDataId) {
        return sleepService.getSleepDataById(sleepDataId);
    }

    @GetMapping("/name/{name}")
    public List<SleepData> getSleepRecordByName(@PathVariable String name) {
        return sleepService.getSleepRecordByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SleepData addSleepRecord(@RequestBody SleepData sleepData) {
        try {
            return sleepService.addSleepRecord(sleepData);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{sleepDataId}")
    @ResponseStatus(HttpStatus.OK)
    public SleepData updateSleepRecord(@PathVariable Long sleepDataId, @RequestBody SleepData sleepData) {
        try {
            return sleepService.updateSleepRecord(sleepDataId, sleepData);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @DeleteMapping("/{sleepDataId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSleepRecord(@PathVariable Long sleepDataId) {
        try {
            sleepService.deleteSleepRecord(sleepDataId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @GetMapping("/targetSleepDuration")
    @ResponseStatus(HttpStatus.OK)
    public Duration targetSleepDuration(@RequestBody SleepData sleepData) {
        try {
            return sleepService.targetSleepDuration(sleepData);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }



}

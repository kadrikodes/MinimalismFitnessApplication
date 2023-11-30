package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.WalkingData;
import com.health.MinimalismFitnessApp.services.UserService;
import com.health.MinimalismFitnessApp.services.WalkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/walking")
public class WalkingController {

    WalkingService walkingService;

    @Autowired
    public WalkingController(WalkingService walkingService) {
        this.walkingService = walkingService;
    }

    @GetMapping
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

//    @GetMapping("/walking/sorted")
//    public List<WalkingData> getSortedWalkingData() {
//        return walkingService.getEntriesSortedByData();
//    }

    @PutMapping("/{walkingId}")
    public WalkingData updateWalkingData(@PathVariable long walkingId, @RequestBody WalkingData walkingData) {
        return walkingService.updateWalkingData(walkingId, walkingData);
    }

    @DeleteMapping("/{walkingId}")
    public ResponseEntity<String> deleteWalkingData(@PathVariable long walkingId) {
        walkingService.deleteWalkingTracker(walkingId);
        return ResponseEntity.ok("Walking data deleted successfully");
    }
}

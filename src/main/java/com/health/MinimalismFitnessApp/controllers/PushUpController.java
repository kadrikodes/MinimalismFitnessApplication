package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.PushUpData;
import com.health.MinimalismFitnessApp.services.PushUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pushups")
public class PushUpController {

    PushUpService pushUpService;
    @Autowired
    public PushUpController(PushUpService pushUpService) {
        this.pushUpService = pushUpService;
    }
    @GetMapping
    public Iterable<PushUpData> getAllPushUpData() {
        return pushUpService.findAll();
    }
    @GetMapping("/user/{pushUpId}")
    public PushUpData getPushUpDataById(@PathVariable long pushUpId) {
        return this.pushUpService.getPushUpDataById(pushUpId);
    }
    @GetMapping("/{userName}")
    public List<PushUpData> getPushUpDataByUserName(@PathVariable String userName) {
        return this.pushUpService.getPushUpDataByUserDataName(userName);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PushUpData addPushUpData(@RequestBody PushUpData pushUpData) {
        PushUpData newPushUp;

        try {
            newPushUp = this.pushUpService.addPushUpData(pushUpData);
        } catch (IllegalAccessError e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return newPushUp;
    }
    @DeleteMapping("/user/{pushUpId}")
    public void deletePushUpData(@PathVariable long pushUpId) {
        PushUpService.delete(pushUpId);
    }

//    @PutMapping("/{update}")
//    public PushUpData updatePushUpData(@RequestBody PushUpData pushUpData) {
//        PushUpService.saveOrUpdate(pushUpData);
//        return pushUpData;
//    }

    @PutMapping("/{pushUpId}")
    public ResponseEntity<PushUpData> updatePushUpData(@PathVariable long pushUpId, @RequestBody PushUpData pushUpData) {
        PushUpData existingPushUpData = pushUpService.getPushUpDataById(pushUpId);

        if (existingPushUpData == null) {

            return ResponseEntity.notFound().build();
        }

        existingPushUpData.setNumberOfPushUps(pushUpData.getNumberOfPushUps());
        existingPushUpData.setTarget(pushUpData.getTarget());
        existingPushUpData.setTimeDuration(pushUpData.getTimeDuration());
        existingPushUpData.setCaloriesBurnt(pushUpData.getCaloriesBurnt());

        pushUpService.saveOrUpdate(existingPushUpData);

        return ResponseEntity.ok(existingPushUpData);
    }
}

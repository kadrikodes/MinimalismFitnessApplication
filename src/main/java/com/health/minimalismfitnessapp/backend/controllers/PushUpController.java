package com.health.minimalismfitnessapp.backend.controllers;

import com.health.minimalismfitnessapp.backend.entities.PushUpData;
import com.health.minimalismfitnessapp.backend.services.PushUpService;
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
    @GetMapping("/user/{id}")
    public PushUpData getPushUpDataById(@PathVariable long id) {
        return this.pushUpService.getPushUpDataById(id);
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
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNutritionData(@PathVariable long id){
        try{
            pushUpService.deletePushUpData(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @PutMapping("/{id}")
    public PushUpData updatePushUpData(@PathVariable long id, @RequestBody PushUpData pushUpData) {
        return pushUpService.updatePushUpData(id, pushUpData);
    }

}

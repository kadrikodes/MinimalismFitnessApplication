package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.PushUpData;
import com.health.MinimalismFitnessApp.services.PushUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<PushUpData> getPushUpDataById(@PathVariable long id) {
        return (List<PushUpData>) this.pushUpService.getPushUpDataById(id);
    }
    @GetMapping("{/{user}")
    public List<PushUpData> getPushUpDataByUserName(@PathVariable String name) {
        return this.pushUpService.getPushUpDataByUserDataName(name);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PushUpData addPushUp(@RequestBody PushUpData pushUpData) {
        PushUpData newPushUp;

        try {
            newPushUp = this.pushUpService.addPushUpData(pushUpData);
        } catch (IllegalAccessError e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return newPushUp;
    }
    @DeleteMapping("/user/{delete}")
    public void deletePushUpData(@PathVariable("delete") long delete) {
        PushUpService.delete(delete);
    }
}

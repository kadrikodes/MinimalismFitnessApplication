package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.PushUpData;
import com.health.MinimalismFitnessApp.services.PushUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return (List<PushUpData>) this.pushUpService.getPushUpTrackerById(id);
    }
    @GetMapping("{/{user}")
    public List<PushUpData> getPushUpDataByUserName(@PathVariable String name) {
        return this.pushUpService.getPushUpTrackerByUserName(name);
    }
}

package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.services.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {

    NutritionService nutritionService;

    @Autowired
    public NutritionController(NutritionService nutritionService) { this.nutritionService = nutritionService; }

    @GetMapping("")
    public List<NutritionData> getAllNutritionData() { return nutritionService.findAll();}

    @GetMapping("/{nutritionID}")
    public NutritionData getNutritionDataByID(@PathVariable long nutritionID){
        return nutritionService.getNutritionID(nutritionID);
    }


    @GetMapping("/user/name/{name}")
    public List<NutritionData> getNutritionDataByUserName(@PathVariable String name){
        return nutritionService.getNutritionByName(name);
    }

    @PostMapping("/addNutritionData")
    public NutritionData addNutritionData(@RequestBody NutritionData nutritionData){
        return nutritionService.addNutritionData(nutritionData);
    }

    @DeleteMapping("/{nutritionID}")
    public void deleteNutritionData(@PathVariable long nutritionID){
        nutritionService.deleteNutritionData(nutritionID);
    }

    @PutMapping("/{nutritionID}")
    public NutritionData updateNutritionData(@PathVariable long nutritionID, @RequestBody NutritionData nutritionData){
        nutritionService.updateNutritionData(nutritionID, nutritionData);
    }

}

package com.health.minimalismFitnessApp.controllers;

import com.health.minimalismFitnessApp.entities.NutritionData;
import com.health.minimalismFitnessApp.services.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNutritionData(@PathVariable long nutritionID){
        try{
            nutritionService.deleteNutritionData(nutritionID);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @PutMapping("/{nutritionID}")
    public NutritionData updateNutritionData(@PathVariable long nutritionID, @RequestBody NutritionData nutritionData){
        return nutritionService.updateNutritionData(nutritionID, nutritionData);
    }

}

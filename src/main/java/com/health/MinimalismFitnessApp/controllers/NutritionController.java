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
        NutritionData nutritionData = nutritionService.getNutritionID(nutritionID);
        if(nutritionData == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nutrition data not found");}

        return nutritionData;
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
    public ResponseEntity<Long> deleteNutritionData(@PathVariable long nutritionID){
        boolean validID = nutritionService.checkNutritionDataExistsByID(nutritionID);

        if (validID){
            nutritionService.deleteNutritionData(nutritionID);
            return new ResponseEntity<>(nutritionID, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

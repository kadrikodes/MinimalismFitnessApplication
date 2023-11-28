package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.services.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {

    NutritionService nutritionService;

    @Autowired
    public NutritionController(NutritionService nutritionService) { this.nutritionService = nutritionService; }

    @GetMapping("")
    public List<NutritionData> getAllNutritionData() { return nutritionService.findAll();}




}

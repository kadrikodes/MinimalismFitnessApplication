package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.entities.UserData;
import com.health.MinimalismFitnessApp.services.NutritionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class NutritionControllerNoSpringTest {

    NutritionService mockNutritionService;
    NutritionController nutritionController;

    @BeforeEach
    void beforeEach(){
        this.mockNutritionService = mock(NutritionService.class);
        this.nutritionController = new NutritionController(mockNutritionService);
    }

    @Test
    void getAllNutritionRecords(){
        List<NutritionData> nutritionRecords = nutritionController.getAllNutritionData();
        verify(mockNutritionService, times(1)).findAll();
    }

    @Test
    void getNutritonRecordById(){
        when(mockNutritionService.getNutritionID(1000L)).thenReturn(new NutritionData(1000L, "Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData()));
        nutritionController.getNutritionDataByID(1000L);

        verify(mockNutritionService, times(1)).getNutritionID(1000L);
    }







}

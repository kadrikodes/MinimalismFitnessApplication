package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.entities.UserData;
import com.health.MinimalismFitnessApp.services.NutritionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
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

    @Test
    void addingANutritionRecord() throws URISyntaxException{
        NutritionData nutritionData = new NutritionData(1000L, "Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData());
        nutritionController.addNutritionData(nutritionData);
        verify(mockNutritionService, times(1)).addNutritionData(nutritionData);
    }

    @Test
    void updatingNutritionRecord() throws URISyntaxException{
        NutritionData nutritionData = new NutritionData(1000L, "Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData());
        nutritionController.addNutritionData(nutritionData);
        NutritionData updatedNutritionData = new NutritionData(1000L, "Burger", 600, 50, 30, 20, "Lunch", new UserData());
        nutritionController.updateNutritionData(1000L, updatedNutritionData);
        verify(mockNutritionService, times(1)).addNutritionData(nutritionData);
        verify(mockNutritionService, times(1)).updateNutritionData(1000L, updatedNutritionData);
    }







}

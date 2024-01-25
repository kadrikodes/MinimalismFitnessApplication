package com.health.minimalismfitnessapp.controllers;

import com.health.minimalismfitnessapp.entities.NutritionData;
import com.health.minimalismfitnessapp.entities.UserData;
import com.health.minimalismfitnessapp.services.NutritionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.time.LocalDate;
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
        when(mockNutritionService.getNutritionID(1000L)).thenReturn(new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData()));
        nutritionController.getNutritionDataByID(1000L);
        verify(mockNutritionService, times(1)).getNutritionID(1000L);
    }

    @Test
    void getNutritionRecordByName(){
<<<<<<< HEAD:src/test/java/com/health/MinimalismFitnessApp/controllers/NutritionControllerNoSpringTest.java
        UserData userData = new UserData("Rais", 180, 85, LocalDate.of(2000,1,1), "MALE");
=======
        UserData userData = new UserData("Rais", 1L, 180, 85, LocalDate.of(2000,1,1), "MALE");
>>>>>>> master:src/test/java/com/health/minimalismfitnessapp/controllers/NutritionControllerNoSpringTest.java
        NutritionData nutritionData = new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", userData);
        nutritionController.addNutritionData(nutritionData);
        mockNutritionService.getNutritionByName("Rais");
        verify(mockNutritionService, times(1)).getNutritionByName("Rais");


    }

    @Test
    void addingANutritionRecord() throws URISyntaxException{
        NutritionData nutritionData = new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData());
        nutritionController.addNutritionData(nutritionData);
        verify(mockNutritionService, times(1)).addNutritionData(nutritionData);
    }

    @Test
    void updatingNutritionRecord() throws URISyntaxException{
        NutritionData nutritionData = new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData());
        nutritionController.addNutritionData(nutritionData);
        NutritionData updatedNutritionData = new NutritionData("Burger", 600, 50, 30, 20, "Lunch", new UserData());
        nutritionController.updateNutritionData(1000L, updatedNutritionData);
        verify(mockNutritionService, times(1)).addNutritionData(nutritionData);
        verify(mockNutritionService, times(1)).updateNutritionData(1000L, updatedNutritionData);
    }

    @Test
    void deletingNutritionRecord(){
        NutritionData nutritionData = new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData());
        nutritionController.addNutritionData(nutritionData);
        nutritionController.deleteNutritionData(1000L);
        verify(mockNutritionService, times(1)).deleteNutritionData(1000L);
    }

}

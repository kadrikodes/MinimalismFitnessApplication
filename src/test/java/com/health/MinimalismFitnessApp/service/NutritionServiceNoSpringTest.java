package com.health.MinimalismFitnessApp.service;

import com.health.MinimalismFitnessApp.TestConstantsNutrition;
import com.health.MinimalismFitnessApp.dataaccess.INutritionRepository;
import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.NutritionData;
import com.health.MinimalismFitnessApp.entities.UserData;
import com.health.MinimalismFitnessApp.services.NutritionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class NutritionServiceNoSpringTest {
    INutritionRepository mockNutritionRepo;
    IUserRepository mockUserRepo;
    NutritionService nutritionService;

    @BeforeEach
    void beforeEach(){
        this.mockNutritionRepo = mock(INutritionRepository.class);
        this.mockUserRepo = mock(IUserRepository.class);
        this.nutritionService =  new NutritionService(mockNutritionRepo, mockUserRepo);
    }

    @Test
    void findAll(){
        List<NutritionData> nutritionDataList = TestConstantsNutrition.getNutritionList();
        when(mockNutritionRepo.findAll()).thenReturn(nutritionDataList);
        List<NutritionData> actualNutritionDataList = nutritionService.findAll();

        verify(mockNutritionRepo, times(1)).findAll();
        Assertions.assertEquals(nutritionDataList, actualNutritionDataList);
    }

    @Test
    void getNutritionDataRecordByInvalidId(){
        when(this.mockNutritionRepo.findById(1L)).thenReturn(Optional.empty());

        NutritionData actual = nutritionService.getNutritionID(1L);

        Assertions.assertNull(actual);
    }

//    @Test
//    void getNutritionRecordByName(){
//        UserData userData = new UserData("Rais", 1L, 180, 85, LocalDate.of(2000,1,1), "MALE");
//        NutritionData nutritionData = new NutritionData(1000L, "Pounded Yam", 600, 20, 60, 20, "Dinner", userData);
//        nutritionController.addNutritionData(nutritionData);
//        mockNutritionService.getNutritionByName("Rais");
//        verify(mockNutritionService, times(1)).getNutritionByName("Rais");
//
//
//    }
//
//    @Test
//    void addingANutritionRecord() throws URISyntaxException {
//        NutritionData nutritionData = new NutritionData(1000L, "Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData());
//        nutritionController.addNutritionData(nutritionData);
//        verify(mockNutritionService, times(1)).addNutritionData(nutritionData);
//    }
//
//    @Test
//    void updatingNutritionRecord() throws URISyntaxException{
//        NutritionData nutritionData = new NutritionData(1000L, "Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData());
//        nutritionController.addNutritionData(nutritionData);
//        NutritionData updatedNutritionData = new NutritionData(1000L, "Burger", 600, 50, 30, 20, "Lunch", new UserData());
//        nutritionController.updateNutritionData(1000L, updatedNutritionData);
//        verify(mockNutritionService, times(1)).addNutritionData(nutritionData);
//        verify(mockNutritionService, times(1)).updateNutritionData(1000L, updatedNutritionData);
//    }
//
//    @Test
//    void deletingNutritionRecord(){
//        NutritionData nutritionData = new NutritionData(1000L, "Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData());
//        nutritionController.addNutritionData(nutritionData);
//        nutritionController.deleteNutritionData(1000L);
//        verify(mockNutritionService, times(1)).deleteNutritionData(1000L);
//    }
//


}

package com.health.minimalismfitnessapp.service;

import com.health.minimalismfitnessapp.TestConstantsNutrition;
import com.health.minimalismfitnessapp.backend.dataaccess.INutritionRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.entities.NutritionData;
import com.health.minimalismfitnessapp.backend.entities.UserData;
import com.health.minimalismfitnessapp.backend.services.NutritionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collections;
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

    @Test
    void getNutritionDataRecordByValidId(){
        NutritionData nutritionData = new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData("Rais", 180, 85, LocalDate.of(2000,1,1), "MALE"));
        when(this.mockNutritionRepo.findById(1L)).thenReturn(Optional.of(nutritionData));

        NutritionData actual = nutritionService.getNutritionID(1L);

        Assertions.assertEquals(nutritionData.getUser(), actual.getUser());
        Assertions.assertEquals(nutritionData.getId(), actual.getId());
        Assertions.assertEquals(nutritionData, actual);
    }

    @Test
    void getNutritionRecordByName(){
        List<NutritionData>  nutritionData = Collections.singletonList(new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData("Rais", 180, 85, LocalDate.of(2000,1,1), "MALE")));
        when(this.mockNutritionRepo.findNutritionDataByUserDataName("Rais")).thenReturn(nutritionData);

        List<NutritionData> actual = nutritionService.getNutritionByName("Rais");
        Assertions.assertEquals(nutritionData, actual);

    }

    @Test
    void addingANutritionRecord() throws URISyntaxException {
        NutritionData nutritionData = new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData("Rais", 180, 85, LocalDate.of(2000,1,1), "MALE"));
        nutritionService.addNutritionData(nutritionData);
        verify(mockNutritionRepo, times(1)).save(nutritionData);
    }

    @Test
    void updatingNutritionRecord() throws URISyntaxException{
        when(mockNutritionRepo.findById(1L)).thenReturn(Optional.of(new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData("Rais", 180, 85, LocalDate.of(2000,1,1), "MALE"))));
        NutritionData updatedNutritionData = new NutritionData("Pizza", 500, 30, 50, 20, "Dinner", new UserData("Rais", 180, 85, LocalDate.of(2000,1,1), "MALE"));

        when(mockNutritionRepo.save(any(NutritionData.class))).thenReturn(updatedNutritionData);
        nutritionService.updateNutritionData(1L, updatedNutritionData);
        verify(mockNutritionRepo, times(1)).findById(1L);
        verify(mockNutritionRepo, times(1)).save(any(NutritionData.class));
    }

    @Test
    void deletingNutritionRecord(){
        NutritionData nutritionData = new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData());
        when(mockNutritionRepo.findById(1L)).thenReturn(Optional.of(nutritionData));
        when(mockNutritionRepo.existsById(1L)).thenReturn(true);
        nutritionService.deleteNutritionData(1L);
        verify(mockNutritionRepo, times(1)).existsById(1L);
        verify(mockNutritionRepo, times(1)).deleteById(1L);
    }
}

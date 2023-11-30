package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.PushUpData;
import com.health.MinimalismFitnessApp.entities.UserData;
import com.health.MinimalismFitnessApp.entities.WalkingData;
import com.health.MinimalismFitnessApp.services.PushUpService;
import com.health.MinimalismFitnessApp.services.WalkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PushUpControllerNoSpringTest {

    PushUpService mockPushUpService;
    PushUpController pushUpController;
    LocalDate birthDate = LocalDate.of(1997, 06, 11);
    UserData userData = new UserData("Kadri", 1L, 120, 70, birthDate, "Male");

    @BeforeEach
    void beforeEach() {
        this.mockPushUpService = mock(PushUpService.class);
        this.pushUpController = new PushUpController(mockPushUpService);
    }

    @Test
    void testGetAllPushUpData() {
        pushUpController.getAllPushUpData();
        verify(mockPushUpService, times(1)).findAll();
    }

    @Test
    void testGetPushUpDataById() {
        Long id = 4L;

        PushUpData expectedPushUpData = new PushUpData(5, 10, 1.5, 50);

        when(mockPushUpService.getPushUpDataById(id)).thenReturn(expectedPushUpData);
        pushUpController.getPushUpDataById(id);

        verify(mockPushUpService, times(1)).getPushUpDataById(id);
    }

    @Test
    void testGetPushUpDataByName() {
        String userName = "Kadri";

        PushUpData expectedPushUpData = new PushUpData(5,10,1.5,50);

        when(mockPushUpService.getPushUpDataByUserDataName(userName)).thenReturn(Collections.singletonList(expectedPushUpData));
        pushUpController.getPushUpDataByUserName(userName);

        verify(mockPushUpService, times(1)).getPushUpDataByUserDataName(userName);
    }

    @Test
    void testAddPushUpData() {
        PushUpData pushUpData = new PushUpData(5,10,1.5,50);

        when(mockPushUpService.addPushUpData(pushUpData)).thenReturn(pushUpData);

        PushUpData addedPushUpData = pushUpController.addPushUpData(pushUpData);

        verify(mockPushUpService, times(1)).addPushUpData(pushUpData);

        assertEquals(pushUpData, addedPushUpData);
    }

//    @Test
//    void testUpdatePushUpData() {
//        long id = 4L;
//
//        PushUpData pushUpData = new PushUpData(5,10,1.5,50);
//
//        when(mockPushUpService.updatePushUpData(id, pushUpData)).thenReturn(pushUpData);
//
//        ResponseEntity<PushUpData> updatedPushData = pushUpController.updatePushUpData(id, pushUpData);
//
//        verify(mockPushUpService, times(1)).updatePushUpData(id, pushUpData);
//
//        assertEquals(pushUpData, updatedPushData.getBody());
//    }

//    @Test
//    void testDeletePushUpData() {
//        long id = 4;
//
//        ResponseEntity<String> response = pushUpController.deletePushUpData(id);
//
//        verify(mockPushUpService, times(1)).deletePushUpData(id);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Push up data deleted successfully", response.getBody());
//    }

}
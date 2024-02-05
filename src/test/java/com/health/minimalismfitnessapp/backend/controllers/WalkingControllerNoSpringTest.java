package com.health.minimalismfitnessapp.backend.controllers;


import com.health.minimalismfitnessapp.backend.entities.ActivityData;


import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserGender;
import com.health.minimalismfitnessapp.backend.services.WalkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WalkingControllerNoSpringTest {

    WalkingService mockWalkingService;
    WalkingController walkingController;

    LocalDateTime dateTime = LocalDateTime.of(2023, 11, 10, 12, 30);
    LocalDate birthDate = LocalDate.of(1997, 06, 11);

    UserData userData = new UserData("Kadri", 120, 70, birthDate, "Male");
    ActivityData activityData = new ActivityData("Walking");

    UserData userData = new UserData("Kadri", 120, 70, birthDate, UserGender.MALE);


    @BeforeEach
    void beforeEach() {
        this.mockWalkingService = mock(WalkingService.class);
        this.walkingController = new WalkingController(mockWalkingService);
    }

    @Test
    void testGetAllWalkingData() {
        walkingController.getAllWalkingData();
        verify(mockWalkingService, times(1)).findAll();
    }

    @Test
    void testGetWalkingDataById() {
        Long walkingId = 1L;

        WalkingData expectedWalkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData);

        when(mockWalkingService.getWalkingDataById(walkingId)).thenReturn(expectedWalkingData);
        walkingController.getWalkingDataById(walkingId);

        verify(mockWalkingService, times(1)).getWalkingDataById(walkingId);
    }

    @Test
    void testGetWalkingDataByName() {
        String name = "Kadri";

        WalkingData expectedWalkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData);

        when(mockWalkingService.getWalkingDataByUserName(name)).thenReturn(Collections.singletonList(expectedWalkingData));
        walkingController.getWalkingDataByUserName(name);

        verify(mockWalkingService, times(1)).getWalkingDataByUserName(name);
    }

    @Test
    void testAddWalkingData() {
        WalkingData walkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData);

        when(mockWalkingService.addWalkingData(walkingData)).thenReturn(walkingData);

        WalkingData addedWalkingData = walkingController.addWalkingData(walkingData);

        verify(mockWalkingService, times(1)).addWalkingData(walkingData);

        assertEquals(walkingData, addedWalkingData);
    }

    @Test
    void testSearchWalkingData() {
        LocalDateTime dateTime = LocalDateTime.now();
        double distance = 10.0;

        WalkingData expectedResult = new WalkingData (1, 10, 100, 60, 5, dateTime, userData, activityData);

        when(mockWalkingService.searchEntriesByCriteria(dateTime, distance)).thenReturn(Collections.singletonList(expectedResult));

        List<WalkingData> actualResult = walkingController.searchWalkingData(dateTime, distance);

        verify(mockWalkingService, times(1)).searchEntriesByCriteria(dateTime, distance);

        assertEquals(Collections.singletonList(expectedResult), actualResult);
    }

    @Test
    void testUpdateWalkingData() {
        long walkingId = 1L;

        WalkingData walkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData);

        when(mockWalkingService.updateWalkingData(walkingId, walkingData)).thenReturn(walkingData);

        WalkingData updatedWalkingData = walkingController.updateWalkingData(walkingId, walkingData);

        verify(mockWalkingService, times(1)).updateWalkingData(walkingId, walkingData);

        assertEquals(walkingData, updatedWalkingData);
    }

    @Test
    void testDeleteWalkingData() {
        long walkingId = 1L;

        ResponseEntity<String> response = walkingController.deleteWalkingData(walkingId);

        verify(mockWalkingService, times(1)).deleteWalkingTracker(walkingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Walking data deleted successfully", response.getBody());
    }

}

package com.health.minimalismfitnessapp.service;

import com.health.minimalismfitnessapp.TestUtilities;
import com.health.minimalismfitnessapp.backend.dataaccess.IWalkingRepository;
import com.health.minimalismfitnessapp.backend.entities.ActivityData;
import com.health.minimalismfitnessapp.backend.entities.UserData;
import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import com.health.minimalismfitnessapp.backend.services.WalkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WalkingServiceFullSpringTest {

    @MockBean
    IWalkingRepository mockRepo;

    @Autowired
    WalkingService walkingService;
    @Autowired
    TestUtilities testUtilities =new TestUtilities();
    LocalDateTime dateTime = LocalDateTime.of(2023, 11, 10, 12, 30);
    LocalDate birthDate = LocalDate.of(1997, 06, 11);
    UserData userData = new UserData("Kadri", 120, 70, birthDate, "Male");
    ActivityData activityData = new ActivityData("Walking");


    @Test
    void testFindAllWithSpring() {
        List<WalkingData> walkingData = testUtilities.createWalkingData();
        when(mockRepo.findAll()).thenReturn(walkingData);
        List<WalkingData> actualWalkingData = walkingService.findAll();

        assertEquals(walkingData, actualWalkingData);
    }

    @Test
    void testGetWalkingDataById() {
        long walkingId = 1L;
        WalkingData expectedWalkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData);
        when(mockRepo.findById(walkingId)).thenReturn(Optional.of(expectedWalkingData));

        WalkingData actualWalkingData = walkingService.getWalkingDataById(walkingId);

        assertEquals(expectedWalkingData, actualWalkingData);
        verify(mockRepo, times(1)).findById(walkingId);
    }

    @Test
    void testGetWalkingDataByUserName() {
        String name = "Kadri";
        List<WalkingData> expectedWalkingDataList = Collections.singletonList(new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData));
        when(mockRepo.findWalkingDataByUserDataName(name)).thenReturn(expectedWalkingDataList);

        List<WalkingData> actualWalkingDataList = walkingService.getWalkingDataByUserName(name);

        assertEquals(expectedWalkingDataList, actualWalkingDataList);
        verify(mockRepo, times(1)).findWalkingDataByUserDataName(name);
    }

    @Test
    void testAddingWalkingData() {
        WalkingData walkingData = new WalkingData(100, 10, 100, 60, 5, dateTime, userData, activityData);

        when(mockRepo.save(any(WalkingData.class))).thenReturn(walkingData);

        WalkingData result = walkingService.addWalkingData(walkingData);

        LocalDateTime expectedLocalDateTime = LocalDateTime.of(2023, 11, 10, 12, 30);
        UserData expectedUserData = new UserData("Kadri", 120, 70, LocalDate.of(1997, 06, 11), "Male");

        assertNotNull(result);
        assertEquals(10, result.getDistance());
        assertEquals(100, result.getSteps());
        assertEquals(100, result.getCaloriesBurned());
        assertEquals(60, result.getDuration());
        assertEquals(5, result.getSpeed());
        assertEquals(expectedLocalDateTime, result.getDateTime());
//        System.out.println("Expected UserData: " + expectedUserData);
//        System.out.println("Result UserData: " + result.getUserData());
//        assertEquals(expectedUserData, result.getUserData());

        verify(mockRepo, times(1)).save(any(WalkingData.class));
    }

    @Test
    void testSearchWalkingData() {
        LocalDateTime dateTime = LocalDateTime.now();
        double distance = 10.0;

        WalkingData expectedResult = new WalkingData(100, 10, 100, 60, 5, dateTime, userData, activityData);

        when(mockRepo.findByDateTimeAndDistance(any(LocalDateTime.class), anyDouble())).thenReturn(Collections.singletonList(expectedResult));

        List<WalkingData> result = walkingService.searchEntriesByCriteria(dateTime, distance);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getDistance());
        assertEquals(100, result.get(0).getSteps());
        assertEquals(dateTime, result.get(0).getDateTime());
        assertEquals(Collections.singletonList(expectedResult), result);

        verify(mockRepo, times(1)).findByDateTimeAndDistance(any(LocalDateTime.class), anyDouble());
    }

    @Test
    void testUpdateWalkingData() {
        long walkingId = 1L;
        WalkingData updatedWalkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData);
        when(mockRepo.findById(walkingId)).thenReturn(Optional.of(new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData)));
        when(mockRepo.save(any(WalkingData.class))).thenReturn(updatedWalkingData);

        WalkingData actualWalkingData = walkingService.updateWalkingData(walkingId, new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData));

        assertEquals(updatedWalkingData, actualWalkingData);
        verify(mockRepo, times(1)).findById(walkingId);
        verify(mockRepo, times(1)).save(any(WalkingData.class));
    }

    @Test
    void testDeleteWalkingTracker() {
        long walkingId = 1L;
        WalkingData walkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData, activityData);
        when(mockRepo.findById(walkingId)).thenReturn(Optional.of(walkingData));

        walkingService.deleteWalkingTracker(walkingId);

        verify(mockRepo, times(1)).findById(walkingId);
        verify(mockRepo, times(1)).delete(walkingData);
    }
}

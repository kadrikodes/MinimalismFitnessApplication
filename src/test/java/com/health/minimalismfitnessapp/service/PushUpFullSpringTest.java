package com.health.minimalismfitnessapp.service;

import com.health.minimalismfitnessapp.backend.MinimalismFitnessAppApplication;
import com.health.minimalismfitnessapp.backend.dataaccess.IActivityRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IPushUpRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IWalkingRepository;
import com.health.minimalismfitnessapp.backend.entities.ActivityData;
import com.health.minimalismfitnessapp.backend.entities.PushUpData;
import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserGender;
import com.health.minimalismfitnessapp.backend.services.PushUpService;
import com.health.minimalismfitnessapp.backend.services.WalkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = MinimalismFitnessAppApplication.class)
public class PushUpFullSpringTest {
    @MockBean
    IPushUpRepository mockRepo;
    @Autowired
    PushUpService pushUpService;
    ActivityData activityData = new ActivityData("PushUps");

    public ArrayList<PushUpData> createPushUpData() {
        ArrayList<PushUpData> pushUpData = new ArrayList<>();

        PushUpData firstPushUpData = new PushUpData(5, 10, 1.5, 100, new UserData("Kadri", 170, 70, LocalDate.of(1997, 06, 11), UserGender.MALE), activityData);
        pushUpData.add(firstPushUpData);
        PushUpData secondPushUpData = new PushUpData(10, 20, 2.0, 150, new UserData("Salah", 160, 80, LocalDate.of(1992, 06, 15), UserGender.MALE), activityData);
        pushUpData.add(secondPushUpData);

        return pushUpData;
    }

    @Test
    void testFindAllWithSpring() {
        List<PushUpData> pushUpData = createPushUpData();
        when(mockRepo.findAll()).thenReturn(pushUpData);
        List<PushUpData> actualPushUpData = pushUpService.findAll();

        assertEquals(pushUpData, actualPushUpData);
    }

    @Test
    void testGetPushUpDataById() {
        long pushUpId = 1L;
        PushUpData expectedPushUpData = new PushUpData(5, 10, 1.5, 100, new UserData("Kadri", 170, 70, LocalDate.of(1997, 06, 11), UserGender.MALE), activityData);
        when(mockRepo.findById(pushUpId)).thenReturn(Optional.of(expectedPushUpData));

        PushUpData actualPushUpData = pushUpService.getPushUpDataById(pushUpId);

        assertEquals(expectedPushUpData, actualPushUpData);
        verify(mockRepo, times(1)).findById(pushUpId);
    }

    @Test
    void testGetPushUpDataByUserName() {
        String name = "Kadri";
        List<PushUpData> expectedPushUpDataList = Collections.singletonList(new PushUpData(5, 10, 1.5, 100, new UserData("Kadri", 170, 70, LocalDate.of(1997, 06, 11), UserGender.MALE), activityData));
        when(mockRepo.findPushUpDataByUserDataName(name)).thenReturn(expectedPushUpDataList);

        List<PushUpData> actualPushUpDataList = pushUpService.getPushUpDataByUserDataName(name);

        assertEquals(expectedPushUpDataList, actualPushUpDataList);
        verify(mockRepo, times(1)).findPushUpDataByUserDataName(name);
    }

    @Test
    void testAddingPushUpData() {
        PushUpData pushUpData = new PushUpData(5, 10, 1.5, 100, new UserData("Kadri", 170, 70, LocalDate.of(1997, 06, 11), UserGender.MALE), activityData);

        when(mockRepo.save(any(PushUpData.class))).thenReturn(pushUpData);

        PushUpData result = pushUpService.addPushUpData(pushUpData);
        UserData expectedUserData = new UserData("Kadri", 120, 70, LocalDate.of(1997, 06, 11), UserGender.MALE);

        assertNotNull(result);
        assertEquals(5, result.getNumberOfPushUps());
        assertEquals(10, result.getTarget());
        assertEquals(1.5, result.getTimeDuration());
        assertEquals(100, result.getCaloriesBurnt());

        verify(mockRepo, times(1)).save(any(PushUpData.class));
    }
    @Test
    void testDeletePushUpData() {
        long pushUpId = 1L;
        PushUpData pushUpData = new PushUpData(5, 10, 1.5, 100, new UserData("Kadri", 170, 70, LocalDate.of(1997, 06, 11), UserGender.MALE), activityData);
        when(mockRepo.findById(pushUpId)).thenReturn(Optional.of(pushUpData));

        pushUpService.deletePushUpData(pushUpId);

        verify(mockRepo, times(1)).findById(pushUpId);
        verify(mockRepo, times(1)).delete(pushUpData);
    }


}

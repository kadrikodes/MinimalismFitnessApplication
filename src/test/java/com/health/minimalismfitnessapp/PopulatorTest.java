package com.health.minimalismfitnessapp;

import com.health.minimalismfitnessapp.backend.MinimalismFitnessAppApplication;
import com.health.minimalismfitnessapp.backend.Populator;
import com.health.minimalismfitnessapp.backend.dataaccess.*;
import com.health.minimalismfitnessapp.backend.entities.*;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserGender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = MinimalismFitnessAppApplication.class)
public class PopulatorTest {
    @Autowired
    private Populator populator;

    @MockBean
    private IUserRepository userRepository;
    @MockBean
    private INutritionRepository nutritionRepository;
    @MockBean
    private IPushUpRepository pushUpRepository;
    @MockBean
    IWalkingRepository walkingRepository;
    @MockBean
    ISleepRepository sleepRepository;
    @MockBean
    IActivityRepository activityRepository;


    @BeforeEach
    public void setUp() {
        UserData userData = new UserData("Rais", 180, 85, LocalDate.of(2000, 1, 1), UserGender.MALE);
        ActivityData activityData = new ActivityData();
//        ActivityData pushupActivityData = new ActivityData("pushUps");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userData));
        when(activityRepository.findById(anyLong())).thenReturn(Optional.of(activityData));
        when(nutritionRepository.findById(anyLong())).thenReturn(Optional.of(new NutritionData("Pizza", 500, 40, 30, 30, "Lunch", userData)));
        when(pushUpRepository.findById(anyLong())).thenReturn(Optional.of(new PushUpData(5, 10, 1.5, 100, userData, activityData)));
        when(walkingRepository.findById(anyLong())).thenReturn(Optional.of(new WalkingData(150, 15, 120, 70, 6, LocalDateTime.of(2023, 11, 30, 10, 45), userData, activityData)));
        when(sleepRepository.findById(anyLong())).thenReturn(Optional.of(new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData)));
    }

    @Test
    public void testPopulateUserData() {
        populator.populateUserData();

        verify(userRepository, times(5)).save(any(UserData.class));
    }

    @Test
    public void testPopulateNutritionData() {
        populator.populateNutritionData();
        verify(nutritionRepository, atLeastOnce()).save(any(NutritionData.class));

        ArgumentCaptor<NutritionData> captor = ArgumentCaptor.forClass(NutritionData.class);
        verify(nutritionRepository, atLeastOnce()).save(captor.capture());

        List<NutritionData> capturedNutritions = captor.getAllValues();
        assertFalse(capturedNutritions.isEmpty(), "Expected at least one NutritionData object to be saved");

        assertEquals("Pizza", capturedNutritions.get(0).getFoodName());
        assertEquals(500, capturedNutritions.get(0).getCalories());
        assertEquals(40, capturedNutritions.get(0).getProtein());
        assertEquals(30, capturedNutritions.get(0).getCarbohydrates());
        assertEquals(30, capturedNutritions.get(0).getFats());
        assertEquals("Lunch", capturedNutritions.get(0).getMealType());
    }

    @Test
    public void testPopulatePushUpData() {
        populator.populatePushUpData();
        verify(pushUpRepository, atLeastOnce()).save(any(PushUpData.class));

        ArgumentCaptor<PushUpData> captor = ArgumentCaptor.forClass(PushUpData.class);
        verify(pushUpRepository, atLeastOnce()).save(captor.capture());

        List<PushUpData> capturedPushUpData = captor.getAllValues();
        assertFalse(capturedPushUpData.isEmpty(), "Expected at least one PushUp data object to be saved");

        assertEquals(20, capturedPushUpData.get(0).getNumberOfPushUps());
        assertEquals(25, capturedPushUpData.get(0).getTarget());
        assertEquals(5, capturedPushUpData.get(0).getTimeDuration());
        assertEquals(50, capturedPushUpData.get(0).getCaloriesBurnt());
    }

    @Test
    public void testPopulateWalkingData() {
        populator.populateWalkingData();
        verify(walkingRepository, atLeastOnce()).save(any(WalkingData.class));

        ArgumentCaptor<WalkingData> captor = ArgumentCaptor.forClass(WalkingData.class);
        verify(walkingRepository, atLeastOnce()).save(captor.capture());

        List<WalkingData> capturedWalkingData = captor.getAllValues();
        assertFalse(capturedWalkingData.isEmpty(), "Expected at least one Walking data object to be saved");

        assertEquals(1, capturedWalkingData.get(0).getSteps());
        assertEquals(10, capturedWalkingData.get(0).getDistance());
        assertEquals(100, capturedWalkingData.get(0).getCaloriesBurned());
        assertEquals(60, capturedWalkingData.get(0).getDuration());
        assertEquals(5, capturedWalkingData.get(0).getSpeed());
        assertEquals(LocalDateTime.of(2023, 11, 10, 12, 30), capturedWalkingData.get(0).getDateTime());
    }

    @Test
    public void testPopulateSleepData() {
        populator.populateSleepData();
        verify(sleepRepository, atLeastOnce()).save(any(SleepData.class));

        ArgumentCaptor<SleepData> captor = ArgumentCaptor.forClass(SleepData.class);
        verify(sleepRepository, atLeastOnce()).save(captor.capture());

        List<SleepData> capturedSleepData = captor.getAllValues();
        assertFalse(capturedSleepData.isEmpty(), "Expected at least one Sleep data object to be saved");

        assertEquals(LocalDateTime.of(2023, 11, 10,22, 00), capturedSleepData.get(0).getTargetBedtime());
        assertEquals(LocalDateTime.of(2023, 11, 10,07, 00), capturedSleepData.get(0).getTargetWakeUpTime());
        assertEquals(LocalDateTime.of(2023, 11, 10,22, 30), capturedSleepData.get(0).getActualBedtime());
        assertEquals(LocalDateTime.of(2023, 11, 10,07, 30, 15), capturedSleepData.get(0).getActualWakeupTime());
    }
}

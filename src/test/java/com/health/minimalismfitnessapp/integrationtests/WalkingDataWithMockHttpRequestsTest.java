package com.health.minimalismfitnessapp.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.minimalismfitnessapp.backend.MinimalismFitnessAppApplication;
import com.health.minimalismfitnessapp.backend.dataaccess.IActivityRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IWalkingRepository;

import com.health.minimalismfitnessapp.backend.entities.ActivityData;
import com.health.minimalismfitnessapp.backend.entities.PushUpData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;

import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserGender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Sql({"classpath:test-walkingdata.sql"})
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = MinimalismFitnessAppApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class WalkingDataWithMockHttpRequestsTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    IWalkingRepository walkingRepository;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IActivityRepository activityRepository;
    WalkingData walkingData1;
    WalkingData walkingData2;

    @BeforeEach
    public void populateData() {
        ActivityData activityData = new ActivityData("PushUps");
        activityRepository.save(activityData);
        UserData userData = new UserData("Rais", 180, 85, LocalDate.of(2000, 1, 1), UserGender.MALE);
        userRepository.save(userData);
        this.walkingData1 = new WalkingData(150, 15, 120, 70, 6, LocalDateTime.of(2023, 11, 30, 10, 45), userData, activityData);
        walkingRepository.save(this.walkingData1);
        userData = new UserData("Divin", 160, 68, LocalDate.of(1994, 1, 1), UserGender.MALE);
        userRepository.save(userData);
        this.walkingData2 = new WalkingData(200, 20, 150, 90, 7, LocalDateTime.of(2023, 11, 30, 10, 45), userData, activityData);
        walkingRepository.save(this.walkingData2);
    }

    @Test
    public void testGettingAllWalkingData() throws Exception {
        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking/allWalkingData")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        WalkingData[] actualWalkingId = mapper.readValue(contentAsJson, WalkingData[].class);

        assertEquals(150, actualWalkingId[0].getSteps());
        assertEquals(15, actualWalkingId[0].getDistance());
        assertEquals(120, actualWalkingId[0].getCaloriesBurned());
        assertEquals(70, actualWalkingId[0].getDuration());
        assertEquals(6, actualWalkingId[0].getSpeed());
        assertEquals(LocalDateTime.of(2023, 11, 30, 10, 45), actualWalkingId[0].getDateTime());
    }

//    @Test
//    public void testGetWalkingDataById() throws Exception {
//        long walkingId = 1000;
//
//        MvcResult result =
//                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking/" + walkingId)))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andReturn();
//
//        String contentAsJson = result.getResponse().getContentAsString();
//        WalkingData actualWalkingId = mapper.readValue(contentAsJson, WalkingData.class);
//
//        assertEquals(100, actualWalkingId.getSteps());
//        assertEquals(10, actualWalkingId.getDistance());
//        assertEquals(100, actualWalkingId.getCaloriesBurned());
//        assertEquals(60, actualWalkingId.getDuration());
//        assertEquals(5, actualWalkingId.getSpeed());
//        assertEquals(LocalDateTime.of(2023, 11, 29, 11, 33), actualWalkingId.getDateTime());
//    }
//
//    @Test
//    public void testFindWalkingDataByUserName() throws Exception {
//        String userName = "Kadri";
//
//        MvcResult result =
//                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking/name/" + userName)))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andReturn();
//
//        String contentAsJson = result.getResponse().getContentAsString();
//        WalkingData[] actualWalkingId = mapper.readValue(contentAsJson, WalkingData[].class);
//
//        assertEquals(100, actualWalkingId[0].getSteps());
//        assertEquals(10, actualWalkingId[0].getDistance());
//        assertEquals(100, actualWalkingId[0].getCaloriesBurned());
//        assertEquals(60, actualWalkingId[0].getDuration());
//        assertEquals(5, actualWalkingId[0].getSpeed());
//        assertEquals(LocalDateTime.of(2023, 11, 29, 11, 33), actualWalkingId[0].getDateTime());
//    }
//
//    @Test
//    public void testAddingWalkingData() throws Exception {
//
//        UserData newUserData = new UserData("Delima", 170, 120, LocalDate.of(1975, 8, 26), UserGender.MALE);
//        WalkingData newWalkingData = new WalkingData(150, 15, 120, 70, 6, LocalDateTime.of(2023, 11, 30, 10, 45), newUserData, activityData);
//
//
//        String jsonRequest = mapper.writeValueAsString(newWalkingData);
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/walking/addWalkingData")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String contentAsJson = result.getResponse().getContentAsString();
//        WalkingData addedWalkingData = mapper.readValue(contentAsJson, WalkingData.class);
//
//        assertNotNull(addedWalkingData.getId());
//        assertEquals(150, addedWalkingData.getSteps());
//        assertEquals(15, addedWalkingData.getDistance());
//        assertEquals(120, addedWalkingData.getCaloriesBurned());
//        assertEquals(70, addedWalkingData.getDuration());
//        assertEquals(6, addedWalkingData.getSpeed());
//        assertEquals(LocalDateTime.of(2023, 11, 30, 10, 45), addedWalkingData.getDateTime());
//    }
//
//    @Test
//    void testSearchWalkingData() throws Exception {
//        LocalDateTime dateTime = LocalDateTime.of(2023, 11, 29, 11, 33);
//        double distance = 10.0;
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/walking/search")
//                        .param("dateTime", dateTime.toString())
//                        .param("distance", String.valueOf(distance)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        String contentAsJson = result.getResponse().getContentAsString();
//        WalkingData[] actualWalkingData = mapper.readValue(contentAsJson, WalkingData[].class);
//
//        assertNotNull(actualWalkingData);
//        assertEquals(1, actualWalkingData.length);
//        assertEquals(dateTime, actualWalkingData[0].getDateTime());
//        assertEquals(distance, actualWalkingData[0].getDistance());
//    }
//
//    @Test
//    void testUpdateWalkingData() throws Exception {
//        long walkingId = 1000L;
//
//        WalkingData updatedData = new WalkingData(100, 10, 100, 60, 5, LocalDateTime.of(2023,11,29,11,33), new UserData("Kadri", 177,75,LocalDate.of(1997,06,11),UserGender.MALE), activityData);
//
//        updatedData.setDistance(100);
//        updatedData.setSteps(200);
//        updatedData.setCaloriesBurned(200);
//
//        String jsonRequest = mapper.writeValueAsString(updatedData);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/walking/update/" + walkingId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        WalkingData updatedWalkingData = walkingRepository.findById(1000L).orElse(null);
//        assertNotNull(updatedWalkingData);
//        assertEquals(updatedData.getSteps(), updatedWalkingData.getSteps());
//        assertEquals(updatedData.getDistance(), updatedWalkingData.getDistance());
//    }
//
//    @Test
//    void testDeleteWalkingData() throws Exception {
//        long walkingId = 1000L;
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/walking/delete/" + walkingId))
//                .andExpect(status().isOk())
//                .andReturn();
//        WalkingData deletedWalkingData = walkingRepository.findById(1000L).orElse(null);
//        assertNull(deletedWalkingData, "Walking data deleted successfully");
//    }
//
//    @Test
//    void testStepsToBurnCalories() throws Exception {
//        double caloriesToBurn = 500.0;
//
//        MvcResult result =
//                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking/calculateStepsToBurnCalories")
//                        .param("caloriesToBurn", String.valueOf(caloriesToBurn))))
//                        .andExpect(status().isOk())
//                        .andReturn();
//
//        int steps = Integer.parseInt(result.getResponse().getContentAsString());
//        assertTrue(steps > 0);
//    }
//
//    @Test
//    void testCalculateWeightLoss() throws Exception {
//        int steps = 1000;
//
//        MvcResult result =
//                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking/calculateWeightLoss")
//                        .param("stepsTaken", String.valueOf(steps))))
//                        .andExpect(status().isOk())
//                        .andReturn();
//
//        double weightLoss = Double.parseDouble(result.getResponse().getContentAsString());
//        assertTrue(weightLoss > 0);
//    }
//
//    @Test
//    public void testGetTotalCaloriesBurned() throws Exception {
//        long walkingId = 1000L;
//
//        MvcResult result =
//                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking/calories/" + walkingId)))
//                        .andExpect(status().isOk())
//                        .andReturn();
//
//        double totalCaloriesBurned = Double.parseDouble(result.getResponse().getContentAsString());
//        assertTrue(totalCaloriesBurned > 0);
//    }
//
//    @Test
//    void testScheduleWalkReminder() throws Exception {
//        int hours = 1;
//        int minutes = 30;
//
//        MvcResult result =
//                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking/calculateWeightLoss")
//                        .param("stepsTaken", String.valueOf(hours))
//                        .param("minutes", String.valueOf(minutes)))
//                        .andExpect(status().isOk())
//                        .andReturn());
//
//        WalkingData scheduleReminder = walkingRepository.findById(1000L).orElse(null);
//
//        assertNotNull(scheduleReminder);
//    }
//
//    @Test
//    void testHasAchievedDailyGoal() throws Exception {
//        int stepsTaken = 16000;
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/walking/hasAchievedDailyGoal")
//                        .param("stepsTaken", String.valueOf(stepsTaken)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//
//        assertTrue(stepsTaken >= 15000);
//    }
//
//    @Test
//    void testHasNotAchievedDailyGoal() throws Exception {
//        int stepsTaken = 10000;
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/walking/hasAchievedDailyGoal")
//                        .param("stepsTaken", String.valueOf(stepsTaken)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("false"));
//
//        assertFalse(stepsTaken >= 15000);
//    }
//
//    @Test
//    void testHasAchievedWeeklyGoal() throws Exception {
//        int stepsTaken = 110000;
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/walking/hasAchievedWeeklyGoal")
//                        .param("stepsTaken", String.valueOf(stepsTaken)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//
//        assertTrue(stepsTaken >= 105000);
//    }
//
//    @Test
//    void testHasNotAchievedWeeklyGoal() throws Exception {
//        int stepsTaken = 100000;
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/walking/hasAchievedWeeklyGoal")
//                        .param("stepsTaken", String.valueOf(stepsTaken)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("false"));
//
//        assertFalse(stepsTaken >= 105000);
//    }
//
//    @Test
//    void testHasAchievedMonthlyGoal() throws Exception {
//        int stepsTaken = 450000;
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/walking/hasAchievedMonthlyGoal")
//                        .param("stepsTaken", String.valueOf(stepsTaken)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//
//        assertTrue(stepsTaken >= 420000);
//    }
//
//    @Test
//    void testHasNotAchievedMonthlyGoal() throws Exception {
//        int stepsTaken = 400000;
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/walking/hasAchievedMonthlyGoal")
//                        .param("stepsTaken", String.valueOf(stepsTaken)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("false"));
//
//        assertFalse(stepsTaken >= 420000);
//    }


}

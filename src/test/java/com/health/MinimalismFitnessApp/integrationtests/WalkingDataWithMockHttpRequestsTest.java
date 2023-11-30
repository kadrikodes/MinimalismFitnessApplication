package com.health.MinimalismFitnessApp.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.MinimalismFitnessApp.dataaccess.IWalkingRepository;
import com.health.MinimalismFitnessApp.entities.UserData;
import com.health.MinimalismFitnessApp.entities.WalkingData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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

@Sql({"classpath:test-walkingdata.sql"})
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class WalkingDataWithMockHttpRequestsTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    IWalkingRepository walkingRepository;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void testGettingAllWalkingData() throws Exception {
        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        WalkingData[] actualWalkingId = mapper.readValue(contentAsJson, WalkingData[].class);

        assertEquals(100, actualWalkingId[0].getSteps());
        assertEquals(10, actualWalkingId[0].getDistance());
        assertEquals(100, actualWalkingId[0].getCaloriesBurned());
        assertEquals(60, actualWalkingId[0].getDuration());
        assertEquals(5, actualWalkingId[0].getSpeed());
        assertEquals(LocalDateTime.of(2023, 11, 29, 11, 33), actualWalkingId[0].getDateTime());
    }

    @Test
    public void testGetWalkingDataById() throws Exception {
        long walkingId = 1000;

        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking/" + walkingId)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        WalkingData actualWalkingId = mapper.readValue(contentAsJson, WalkingData.class);

        assertEquals(100, actualWalkingId.getSteps());
        assertEquals(10, actualWalkingId.getDistance());
        assertEquals(100, actualWalkingId.getCaloriesBurned());
        assertEquals(60, actualWalkingId.getDuration());
        assertEquals(5, actualWalkingId.getSpeed());
        assertEquals(LocalDateTime.of(2023, 11, 29, 11, 33), actualWalkingId.getDateTime());
    }

    @Test
    public void testFindWalkingDataByUserName() throws Exception {
        String userName = "Kadri";

        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/walking/name/" + userName)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        WalkingData[] actualWalkingId = mapper.readValue(contentAsJson, WalkingData[].class);

        assertEquals(100, actualWalkingId[0].getSteps());
        assertEquals(10, actualWalkingId[0].getDistance());
        assertEquals(100, actualWalkingId[0].getCaloriesBurned());
        assertEquals(60, actualWalkingId[0].getDuration());
        assertEquals(5, actualWalkingId[0].getSpeed());
        assertEquals(LocalDateTime.of(2023, 11, 29, 11, 33), actualWalkingId[0].getDateTime());
    }

    @Test
    public void testAddingWalkingData() throws Exception {
        UserData newUserData = new UserData("Delima", 300L, 170, 120, LocalDate.of(1975, 8, 26), "Male");
        WalkingData newWalkingData = new WalkingData(150, 15, 120, 70, 6, LocalDateTime.of(2023, 11, 30, 10, 45), newUserData);

        String jsonRequest = mapper.writeValueAsString(newWalkingData);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/walking/addWalkingData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        WalkingData addedWalkingData = mapper.readValue(contentAsJson, WalkingData.class);

        assertNotNull(addedWalkingData.getId());
        assertEquals(150, addedWalkingData.getSteps());
        assertEquals(15, addedWalkingData.getDistance());
        assertEquals(120, addedWalkingData.getCaloriesBurned());
        assertEquals(70, addedWalkingData.getDuration());
        assertEquals(6, addedWalkingData.getSpeed());
        assertEquals(LocalDateTime.of(2023, 11, 30, 10, 45), addedWalkingData.getDateTime());
    }

    @Test
    void testSearchWalkingData() throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(2023, 11, 29, 11, 33);
        double distance = 10.0;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/walking/search")
                        .param("dateTime", dateTime.toString())
                        .param("distance", String.valueOf(distance)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        WalkingData[] actualWalkingData = mapper.readValue(contentAsJson, WalkingData[].class);

        assertNotNull(actualWalkingData);
        assertEquals(1, actualWalkingData.length);
        assertEquals(dateTime, actualWalkingData[0].getDateTime());
        assertEquals(distance, actualWalkingData[0].getDistance());
    }

    @Test
    void testUpdateWalkingData() throws Exception {
        long walkingId = 1000L;
        WalkingData updatedData = new WalkingData(100, 10, 100, 60, 5, LocalDateTime.of(2023,11,29,11,33), new UserData("Kadri",100L,177,75,LocalDate.of(1997,06,11),"Male"));
        updatedData.setDistance(100);
        updatedData.setSteps(200);
        updatedData.setCaloriesBurned(200);

        String jsonRequest = mapper.writeValueAsString(updatedData);

        mockMvc.perform(MockMvcRequestBuilders.put("/walking/" + walkingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testDeleteWalkingData() throws Exception {
        long walkingId = 1000L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/walking/" + walkingId))
                .andExpect(status().isOk())
                .andReturn();
        WalkingData deletedWalkingData = walkingRepository.findById(1000L).orElse(null);
        assertNull(deletedWalkingData, "Walking data deleted successfully");
    }
}


package com.health.minimalismfitnessapp.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.health.minimalismfitnessapp.backend.MinimalismFitnessAppApplication;
import com.health.minimalismfitnessapp.backend.dataaccess.ISleepRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.entities.NutritionData;
import com.health.minimalismfitnessapp.backend.entities.SleepData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
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


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = MinimalismFitnessAppApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@TestPropertySource(properties = {"spring.sql.init.mode=never"})
class SleepControllerIntegrationTest {
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ISleepRepository sleepRepository;
    @Autowired
    IUserRepository userRepository;

    SleepData sleepData1;
    SleepData sleepData2;

//    public SleepControllerIntegrationTest() {
//        mapper.registerModule(new JavaTimeModule());
//    }

    @BeforeEach
    public void populateData() {
        UserData userData = new UserData("Rais", 180, 85, LocalDate.of(2000, 1, 1), UserGender.MALE);
        userRepository.save(userData);
        this.sleepData1 = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData);
        sleepRepository.save(this.sleepData1);
        userData = new UserData("Divin", 160, 68, LocalDate.of(1994, 1, 1), UserGender.MALE);
        userRepository.save(userData);
        this.sleepData2 = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData);
        sleepRepository.save(this.sleepData2);
    }

    @Test
    void testGettingAllSleepRecords() throws Exception {
//        long sleepID = this.sleepData1.getId();
        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
//        String contentAsJson = result.getResponse().getContentAsString();

//        SleepData[] sleepData = mapper.readValue(contentAsJson, SleepData[].class);


//        assertEquals("22:00", sleepData[0].getTargetBedtime().toString());
//        assertEquals("07:00", sleepData[0].getTargetWakeUpTime().toString());
//        assertEquals("22:00", sleepData[1].getTargetBedtime().toString());
//        assertEquals("07:00", sleepData[1].getTargetWakeUpTime().toString());

    }

//    @Test
//    void getSleepRecordByName() throws Exception {
//        MvcResult result =
//                (this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker/name/{name}", "User2")))
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                        .andReturn();
//        String contentAsJson = result.getResponse().getContentAsString();
//        SleepData[] sleepData = objectMapper.readValue(contentAsJson, SleepData[].class);
//        assertEquals(53, sleepData[0].getId());
//    }
//
    @Test
    void getSleepRecordById() throws Exception {
        long sleepID =this.sleepData1.getId();

        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker/{sleepDataId}", sleepID)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        String contentAsJson = result.getResponse().getContentAsString();
        SleepData sleepData = objectMapper.readValue(contentAsJson, SleepData.class);
//        assertEquals("User1", sleepData.getUser().getName());
        assertEquals(sleepID, sleepData.getId());
    }
//
    @Test
    void addSleepRecord() throws Exception {
        int initialRecordCount = sleepRepository.findAll().size();

        UserData userData = new UserData("ABC", 167, 67, LocalDate.of(1960,04,11), UserGender.MALE);
        SleepData newSleepData = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData);

        String json = objectMapper.writeValueAsString(newSleepData);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/sleeptracker/addSleepData")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isCreated());

        int finalRecordCount = sleepRepository.findAll().size();
        assertEquals(initialRecordCount+1, finalRecordCount);

    }
//
    @Test
    void updateSleepRecord() throws Exception {
        SleepData updatedSleepData = sleepRepository.findById(52L).orElse(null);
        updatedSleepData.setActualBedtime(LocalDateTime.of(2023, 11, 10,22, 00));
        String json = objectMapper.writeValueAsString(updatedSleepData);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/sleeptracker/{id}",52)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        SleepData updatedSleepRecord = objectMapper.readValue(contentAsJson, SleepData.class);

        assertEquals(LocalTime.of(23, 00), updatedSleepRecord.getActualBedtime());
    }

    @Test
    void deleteSleepRecord() throws Exception {
        long sleepID = this.sleepData1.getId();
        SleepData deletedSleepData = sleepRepository.findById(sleepID).orElse(null);

        mockMvc.perform(delete("/sleeptracker/{id}", sleepID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        SleepData checkingSleepDataAfterDeletion = sleepRepository.findById(sleepID).orElse(null);
        assertNull(checkingSleepDataAfterDeletion, "Sleep Record Deleted");
    }

    @Test
    void testTargetSleepDuration() throws Exception {
        long sleepID = this.sleepData1.getId();
        SleepData sleepData = sleepRepository.findById(sleepID).orElse(null);
        String json = objectMapper.writeValueAsString(sleepData);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker/targetSleepDuration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        Duration targetSleepDuration = objectMapper.readValue(contentAsJson, Duration.class);
        assertEquals(Duration.ofHours(9), targetSleepDuration);
        assertNotNull(targetSleepDuration);
    }

}
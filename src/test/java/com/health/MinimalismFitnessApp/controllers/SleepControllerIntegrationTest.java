package com.health.MinimalismFitnessApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.health.MinimalismFitnessApp.dataaccess.ISleepRepository;
import com.health.MinimalismFitnessApp.entities.SleepData;
import com.health.MinimalismFitnessApp.entities.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Sql("classpath:test-sleep-data.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
class SleepControllerIntegrationTest {
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ISleepRepository sleepRepository;

    public SleepControllerIntegrationTest() {
        mapper.registerModule(new JavaTimeModule());
    }


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllSleepRecords() throws Exception {
        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        String contentAsJson = result.getResponse().getContentAsString();

        SleepData[] sleepData = mapper.readValue(contentAsJson, SleepData[].class);

        assertEquals("22:00", sleepData[0].getTargetBedtime().toString());
        assertEquals("07:00", sleepData[0].getTargetWakeUpTime().toString());
        assertEquals("22:00", sleepData[1].getTargetBedtime().toString());
        assertEquals("07:00", sleepData[1].getTargetWakeUpTime().toString());

    }

    @Test
    void getSleepRecordByName() throws Exception {
        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker/name/{name}", "ABC")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        String contentAsJson = result.getResponse().getContentAsString();
        SleepData[] sleepData = mapper.readValue(contentAsJson, SleepData[].class);
        assertEquals(10, sleepData[0].getId());
    }

    @Test
    void getSleepRecordById() throws Exception {
        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker/{sleepDataId}", 11L)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        String contentAsJson = result.getResponse().getContentAsString();
        SleepData sleepData = mapper.readValue(contentAsJson, SleepData.class);
        assertEquals("ABC", sleepData.getUser().getName());
        assertEquals(11L, sleepData.getId());
    }

    @Test
    void addSleepRecord() throws Exception {
        int initialRecordCount = sleepRepository.findAll().size();
        UserData userData = new UserData("ABC", 15L, 67, 167, LocalDate.of(1960,04,11),"Male");
        SleepData newSleepData = new SleepData(LocalTime.of( 22,30), LocalTime.of(07, 30), LocalTime.of(22,30), LocalTime.of(07, 00), userData);
        String json = mapper.writeValueAsString(newSleepData);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/sleeptracker")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isCreated());

        int finalRecordCount = sleepRepository.findAll().size();
        assertEquals(initialRecordCount+1, finalRecordCount);
    }

    @Test
    void updateSleepRecord() throws Exception {
        SleepData updatedSleepData = sleepRepository.findById(11L).orElse(null);
        updatedSleepData.setActualBedtime(LocalTime.of(23,00));
        String json = mapper.writeValueAsString(updatedSleepData);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/sleeptracker/{id}",11)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        SleepData updatedSleepRecord = mapper.readValue(contentAsJson, SleepData.class);

        assertEquals(LocalTime.of(23, 00), updatedSleepRecord.getActualBedtime());
    }

    @Test
    void deleteSleepRecord() throws Exception {
        SleepData deletedSleepData = sleepRepository.findById(10L).orElse(null);
        mockMvc.perform(delete("/sleeptracker/{id}", 10)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        SleepData checkingSleepDataAfterDeletion = sleepRepository.findById(10L).orElse(null);
        assertNull(checkingSleepDataAfterDeletion, "Sleep Record Deleted");
    }
}
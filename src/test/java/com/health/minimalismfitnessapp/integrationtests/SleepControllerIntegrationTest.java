
package com.health.minimalismfitnessapp.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.health.minimalismfitnessapp.backend.dataaccess.ISleepRepository;
import com.health.minimalismfitnessapp.backend.entities.SleepData;
import com.health.minimalismfitnessapp.backend.entities.UserData;
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

    @Test
    void testGettingAllSleepRecords() throws Exception {
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
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker/name/{name}", "User2")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        String contentAsJson = result.getResponse().getContentAsString();
        SleepData[] sleepData = mapper.readValue(contentAsJson, SleepData[].class);
        assertEquals(53, sleepData[0].getId());
    }

    @Test
    void getSleepRecordById() throws Exception {
        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker/{sleepDataId}", 51L)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        String contentAsJson = result.getResponse().getContentAsString();
        SleepData sleepData = mapper.readValue(contentAsJson, SleepData.class);
        assertEquals("User1", sleepData.getUser().getName());
        assertEquals(51L, sleepData.getId());
    }

    @Test
    void addSleepRecord() throws Exception {
        int initialRecordCount = sleepRepository.findAll().size();
        UserData userData = new UserData("ABC", 67, 167, LocalDate.of(1960,04,11),"MALE");
        SleepData newSleepData = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData);
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
        SleepData updatedSleepData = sleepRepository.findById(52L).orElse(null);
        updatedSleepData.setActualBedtime(LocalDateTime.of(2023, 11, 10,22, 00));
        String json = mapper.writeValueAsString(updatedSleepData);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/sleeptracker/{id}",52)
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
        SleepData deletedSleepData = sleepRepository.findById(53L).orElse(null);
        mockMvc.perform(delete("/sleeptracker/{id}", 53)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        SleepData checkingSleepDataAfterDeletion = sleepRepository.findById(53L).orElse(null);
        assertNull(checkingSleepDataAfterDeletion, "Sleep Record Deleted");
    }

    @Test
    void testTargetSleepDuration() throws Exception {
        SleepData sleepData = sleepRepository.findById(52L).orElse(null);
        String json = mapper.writeValueAsString(sleepData);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker/targetSleepDuration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        Duration targetSleepDuration = mapper.readValue(contentAsJson, Duration.class);
        assertEquals(Duration.ofHours(9), targetSleepDuration);
        assertNotNull(targetSleepDuration);
    }

}
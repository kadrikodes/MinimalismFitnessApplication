
package com.health.minimalismfitnessapp.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.health.minimalismfitnessapp.backend.MinimalismFitnessAppApplication;
import com.health.minimalismfitnessapp.backend.dataaccess.ISleepRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.LocalDate;
import java.time.LocalDateTime;


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


    UserData userData1;
    UserData userData2;

    SleepData sleepData1;
    SleepData sleepData2;


    @BeforeEach
    public void populateData() {
        this.userData1 = new UserData("Rais", 180, 85, LocalDate.of(2000, 1, 1), UserGender.MALE);
        userRepository.save(this.userData1);
        this.sleepData1 = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData1);
        sleepRepository.save(this.sleepData1);
        this.userData2 = new UserData("Divin", 160, 68, LocalDate.of(1994, 1, 1), UserGender.MALE);
        userRepository.save(this.userData2);
        this.sleepData2 = new SleepData(LocalDateTime.of(2023, 11, 10,22, 00), LocalDateTime.of(2023, 11, 10,07, 00), LocalDateTime.of(2023, 11, 10,22, 30), LocalDateTime.of(2023, 11, 10,07, 30, 15), userData2);
        sleepRepository.save(this.sleepData2);
    }

    @Test
    void testGettingAllSleepRecords() throws Exception {
        long sleepID1 = this.sleepData1.getId();
        LocalDateTime targetBedTime1 = this.sleepData1.getTargetBedtime();
        LocalDateTime targetWakeUpTime1 = this.sleepData1.getTargetWakeUpTime();

        long sleepID2 = this.sleepData2.getId();
        LocalDateTime targetBedTime2 = this.sleepData2.getTargetBedtime();
        LocalDateTime targetWakeUpTime2 = this.sleepData2.getTargetWakeUpTime();


        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/sleeptracker/allSleepingData")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();
        String contentAsJson = result.getResponse().getContentAsString();

        SleepData[] sleepData = objectMapper.readValue(contentAsJson, SleepData[].class);
        assertEquals(sleepID1, sleepData[0].getId());
        assertEquals(targetBedTime1, sleepData[0].getTargetBedtime());
        assertEquals(targetWakeUpTime1, sleepData[0].getTargetWakeUpTime());
        assertEquals(sleepID2, sleepData[1].getId());
        assertEquals(targetBedTime2, sleepData[1].getTargetBedtime());
        assertEquals(targetWakeUpTime2, sleepData[1].getTargetWakeUpTime());

    }


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
        assertEquals(sleepID, sleepData.getId());
    }

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
void updateSleepRecord_Success() throws Exception {
    long sleepID = this.sleepData1.getId();
    SleepData originalSleepData = sleepRepository.findById(sleepID).orElse(null);

    LocalDateTime newActualBedtime = LocalDateTime.of(2023, 11, 10, 22, 00);
    originalSleepData.setActualBedtime(newActualBedtime);

    String json = objectMapper.writeValueAsString(originalSleepData);

    MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.put("/sleeptracker/{id}", sleepID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isOk())
            .andReturn();

    String contentAsJson = result.getResponse().getContentAsString();
    SleepData updatedSleepRecord = objectMapper.readValue(contentAsJson, SleepData.class);

    assertEquals(newActualBedtime, updatedSleepRecord.getActualBedtime());
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

}
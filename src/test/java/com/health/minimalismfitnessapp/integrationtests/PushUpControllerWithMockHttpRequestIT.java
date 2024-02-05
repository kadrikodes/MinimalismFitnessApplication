
package com.health.minimalismfitnessapp.integrationtests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.health.minimalismfitnessapp.backend.MinimalismFitnessAppApplication;
import com.health.minimalismfitnessapp.backend.dataaccess.IActivityRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IPushUpRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.entities.ActivityData;
import com.health.minimalismfitnessapp.backend.entities.PushUpData;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@Sql("classpath:pushup-test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = MinimalismFitnessAppApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class PushUpControllerWithMockHttpRequestIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    IPushUpRepository pushUpRepository;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IActivityRepository activityRepository;

    PushUpData pushUpData1;
    PushUpData pushUpData2;

    @BeforeEach
    public void populateData() {
        ActivityData activityData = new ActivityData("PushUps");
        activityRepository.save(activityData);
        UserData userData = new UserData("Rais", 180, 85, LocalDate.of(2000, 1, 1), UserGender.MALE);
        userRepository.save(userData);
        this.pushUpData1 = new PushUpData(5, 10, 1.5, 100, userData, activityData);
        pushUpRepository.save(this.pushUpData1);
        userData = new UserData("Divin", 160, 68, LocalDate.of(1994, 1, 1), UserGender.MALE);
        userRepository.save(userData);
        this.pushUpData2 = new PushUpData(10, 20, 2.0, 150, userData, activityData);
        pushUpRepository.save(this.pushUpData2);
    }

    @Test
    public void testGetAllPushUpData() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pushups"))
                .andExpect(status().isOk())
                .andExpect((content().contentType(MediaType.APPLICATION_JSON)))
                .andReturn();
    }
    @Test
    public void testDeletePushUpData() throws Exception {
        long pushUpId = pushUpData1.getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/pushups/" + pushUpId))
                .andExpect(status().isNoContent())
                .andExpect(content().string(emptyOrNullString()));

        PushUpData checkPushUpDataAfterDeletion = pushUpRepository.findById(pushUpId).orElse(null);
        assertNull(checkPushUpDataAfterDeletion, "PushUp Data Deleted");
    }

    @Test
    void testUpdatePushUpData() throws Exception {
        long pushupId = this.pushUpData1.getId();

        String json = mapper.writeValueAsString(pushUpData1);

        mockMvc.perform(MockMvcRequestBuilders.put("/pushups/" + pushupId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/pushups/user/" + pushupId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(pushUpData1)))
                .andReturn();
    }



    @Test
    public void testGetPushUpDataById() throws Exception {
        long pushUpId = this.pushUpData1.getId();

        ResultActions resultActions =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/pushups/user/" + pushUpId ));
        resultActions.andExpect(status().isOk());

        resultActions.andExpect((content().contentType(MediaType.APPLICATION_JSON)));
        MvcResult mvcResult = resultActions.andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        PushUpData pushUpData = mapper.readValue(json, PushUpData.class);
        assertNotNull(pushUpData);
        assertEquals(pushUpId, pushUpData.getId());
    }

    @Test
    public void testFindPushUpDataByUserName() throws Exception {
        String userName = pushUpData1.getUser().getName();

        mapper.registerModule(new JavaTimeModule());

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/pushups/" + userName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        PushUpData[] actualPushUpData = mapper.readValue(contentAsJson, PushUpData[].class);

        if (actualPushUpData.length > 0) {
            assertEquals(5, actualPushUpData[0].getNumberOfPushUps());
            assertEquals(10, actualPushUpData[0].getTarget());
            assertEquals(1.5, actualPushUpData[0].getTimeDuration());
            assertEquals(100, actualPushUpData[0].getCaloriesBurnt());
        } else {
            fail("No PushUpData found for the specified user name");
        }
    }

    @Test
    public void testAddPushUpData() throws Exception {
        int originalNumOfPushUpData = getNumberOfPushUpData();

        ActivityData activityData = new ActivityData("PushUps");
        activityRepository.save(activityData);
        UserData testUser = new UserData("Dwayne", 174, 82.5, LocalDate.of(1996,10,12), UserGender.MALE);
        PushUpData newPushUpData = new PushUpData(5, 10, 1.5, 50, testUser, activityData);

        String json = mapper.writeValueAsString(newPushUpData);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/pushups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        PushUpData pushUpData = mapper.readValue(contentAsString, PushUpData.class);

        assertAll("testing add a Pushup data record",
                () -> assertEquals(originalNumOfPushUpData + 1, getNumberOfPushUpData()),
                () -> assertTrue(checkIfOnList(newPushUpData))
        );
    }

    private int getNumberOfPushUpData() throws Exception {
        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/pushups")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        PushUpData[] pushUpData = mapper.readValue(contentAsString, PushUpData[].class);
        return pushUpData.length;
    }

    private boolean checkIfOnList(PushUpData pushUpData) throws Exception {
        boolean isOnList = false;
        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/pushups")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        PushUpData[] allPushUpData = mapper.readValue(contentAsString, PushUpData[].class);

        for(PushUpData n : allPushUpData) {
            if (n.getUser().getName().equals(pushUpData.getUser().getName()) && n.getUser().getBirthdate().equals(pushUpData.getUser().getBirthdate())) {
                isOnList = true;
            }
        }return isOnList;
    }

}

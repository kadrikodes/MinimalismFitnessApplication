package com.health.MinimalismFitnessApp.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.health.MinimalismFitnessApp.dataaccess.IPushUpRepository;
import com.health.MinimalismFitnessApp.entities.PushUpData;
import com.health.MinimalismFitnessApp.entities.UserData;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:pushup-test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
class PushUpWithMockHttpRequestIT {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    IPushUpRepository pushUpRepository;

    @Test
    public void testGetAllPushUpData() throws Exception {
        final String expectedJson = """
                [{"numberOfPushUps": 5, "target": 10, "timeDuration": 1.5, "caloriesBurnt": 50, "id": 4}]
                """;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pushups"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(expectedJson))
                .andReturn();
    }
    @Test
    public void testDeletePushUpData() throws Exception {
        PushUpData deletedPushUpData = pushUpRepository.findById(4L).orElse(null);
        //assertNotNull(deletedPushUpData, "PushUp Data exists before deletion");

        mockMvc.perform(MockMvcRequestBuilders.delete("/pushups/user/{delete}", 4)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        PushUpData checkPushUpDataAfterDeletion = pushUpRepository.findById(4L).orElse(null);
        assertNull(checkPushUpDataAfterDeletion, "PushUp Data Deleted");
    }

    @Test
    void testUpdatePushUpData() throws Exception {
        long id = 4;
        PushUpData pushUpData = new PushUpData(5, 10, 1.5, 100); new UserData("Kadri",100L,177,75, LocalDate.of(1997,06,11),"Male");
        pushUpData.setNumberOfPushUps(10);
        pushUpData.setTarget(20);
        pushUpData.setTimeDuration(2.5);
        pushUpData.setCaloriesBurnt(200);

        String jsonRequest = mapper.writeValueAsString(pushUpData);

        mockMvc.perform(MockMvcRequestBuilders.put("/pushups/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testGetPushUpDataById() throws Exception {
        long id = 4;

        mapper.registerModule(new JavaTimeModule());

        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/pushups/user/" + id)))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        PushUpData actualPushUpId = mapper.readValue(contentAsJson, PushUpData.class);

        assertEquals(5, actualPushUpId.getNumberOfPushUps());
        assertEquals(10, actualPushUpId.getTarget());
        assertEquals(1.5, actualPushUpId.getTimeDuration());
        assertEquals(50, actualPushUpId.getCaloriesBurnt());
    }

    @Test
    public void testFindPushUpDataByUserName() throws Exception {
        String userName = "Kadri";

        mapper.registerModule(new JavaTimeModule());

        MvcResult result =
                (this.mockMvc.perform(MockMvcRequestBuilders.get("/pushups/" + userName)))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                        .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        PushUpData[] actualPushUpData = mapper.readValue(contentAsJson, PushUpData[].class);

        if (actualPushUpData.length > 0) {
            assertEquals(5, actualPushUpData[0].getNumberOfPushUps());
            assertEquals(10, actualPushUpData[0].getTarget());
            assertEquals(1.5, actualPushUpData[0].getTimeDuration());
            assertEquals(50, actualPushUpData[0].getCaloriesBurnt());
        } else {
            fail("No PushUpData found for the specified user name");
        }
    }

    @Test
    public void testAddPushUpData() throws Exception {
        UserData newUserData = new UserData("Delima", 300L, 170, 120, LocalDate.of(1975, 8, 26), "Male");
        PushUpData newPushUpData = new PushUpData(5, 10, 1.5, 50);

        String jsonRequest = mapper.writeValueAsString(newPushUpData);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pushups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())  // Update this line
                .andReturn();

        String contentAsJson = result.getResponse().getContentAsString();
        PushUpData addedPushUpData = mapper.readValue(contentAsJson, PushUpData.class);

        assertNotNull(addedPushUpData.getId());
        assertEquals(5, addedPushUpData.getNumberOfPushUps());
        assertEquals(10, addedPushUpData.getTarget());
        assertEquals(1.5, addedPushUpData.getTimeDuration());
        assertEquals(50, addedPushUpData.getCaloriesBurnt());
    }
}










package com.health.minimalismfitnessapp.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.minimalismfitnessapp.entities.NutritionData;
import com.health.minimalismfitnessapp.entities.UserData;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;


import static com.health.minimalismfitnessapp.TestConstantsNutrition.EXPECTED_ALL_NUTRITION_JSON;
import static com.health.minimalismfitnessapp.TestConstantsNutrition.EXPECTED_ONE_NUTRITION_JSON;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("classpath:nutrition-test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class NutritionWithMockHttpRequestIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void gettingAllNutritionData() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/nutrition"))
                        .andExpect(status().isOk())
                        .andExpect((content().contentType(MediaType.APPLICATION_JSON)))
                        .andExpect(content().json(EXPECTED_ALL_NUTRITION_JSON))
                        .andReturn();
    }

    @Test
    public  void testGettingOneNutritionRecord() throws Exception {
        long nutritionID = 1000L;

        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/nutrition/" + nutritionID ))
                        .andExpect(status().isOk())
                        .andExpect((content().contentType(MediaType.APPLICATION_JSON)))
                        .andExpect(content().json(EXPECTED_ONE_NUTRITION_JSON))
                        .andReturn();
    }

    @Test
    void testDeletingNutritionRecord() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/nutrition/" + 1000L))
                        .andExpect(status().isNoContent())
                        .andExpect(content().string(emptyOrNullString()));
    }

    @Test
    void testUpdatingNutritionRecord() throws Exception {
        UserData testUser = new UserData("Rais", 1L, 180, 85, LocalDate.of(2000,1,1), "MALE");
        NutritionData updatedNutritionData = new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", testUser);
        updatedNutritionData.setId(1000L);
        String json = objectMapper.writeValueAsString(updatedNutritionData);

        mockMvc.perform(MockMvcRequestBuilders.put("/nutrition/" + 1000L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get("/nutrition/" + 1000L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatedNutritionData)))
                .andReturn();

    }




    @Test
    public void testAddingANutritionRecord() throws Exception {
        int originalNumOfNutritionRecords = getNumberOfNutritionRecords();
        UserData testUser = new UserData("Samuel", 3L, 174, 82.5, LocalDate.of(1996,10,12), "MALE");
        NutritionData testNutritionData = new NutritionData("Burger", 300, 50, 30, 20, "Lunch", testUser);

        String json = objectMapper.writeValueAsString(testNutritionData);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/nutrition/addNutritionData")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        NutritionData nutritionData = objectMapper.readValue(contentAsString, NutritionData.class);

        assertAll("testing add a Nutrition data record",
                () -> assertEquals(originalNumOfNutritionRecords+1, getNumberOfNutritionRecords()),
                () -> assertTrue(checkIfOnList(testNutritionData))
        );
    }

    private int getNumberOfNutritionRecords() throws Exception {
        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/nutrition")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        NutritionData[] nutritionData = objectMapper.readValue(contentAsString, NutritionData[].class);
        return nutritionData.length;
    }

    private boolean checkIfOnList(NutritionData nutritionData) throws Exception {
        boolean isOnList = false;
        ResultActions resultActions =this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/nutrition")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        NutritionData[] allNutritionData = objectMapper.readValue(contentAsString, NutritionData[].class);

        for(NutritionData n : allNutritionData) {
            if (n.getUser().getName().equals(nutritionData.getUser().getName()) && n.getUser().getBirthdate().equals(nutritionData.getUser().getBirthdate())) {
                isOnList = true;
            }
        }return isOnList;
    }
}

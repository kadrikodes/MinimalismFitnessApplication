package com.health.MinimalismFitnessApp.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static com.health.MinimalismFitnessApp.TestConstants.EXPECTED_ALL_NUTRITION_JSON;
import static com.health.MinimalismFitnessApp.TestConstants.EXPECTED_ONE_NUTRITION_JSON;

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
    public  void testGettingOneNutritionData() throws Exception {
        long nutritionID = 1000L;

        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/nutrition/" + nutritionID ))
                        .andExpect(status().isOk())
                        .andExpect((content().contentType(MediaType.APPLICATION_JSON)))
                        .andExpect(content().json(EXPECTED_ONE_NUTRITION_JSON))
                        .andReturn();
    }





}

package com.health.MinimalismFitnessApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.MinimalismFitnessApp.dataaccess.IPushUpRepository;
import com.health.MinimalismFitnessApp.entities.PushUpData;
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

import static com.health.MinimalismFitnessApp.services.PushUpService.delete;
import static org.assertj.core.api.BDDAssertions.and;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@Sql("classpath:test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
class PushUpControllerWithMockHttpRequestIT {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    IPushUpRepository pushUpRepository;

    @Test
    public void testGettingAllPushUpData() throws Exception {
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
        assertNotNull(deletedPushUpData, "PushUp Data exists before deletion");

        mockMvc.perform(MockMvcRequestBuilders.delete("/pushups/{delete}", 4)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        PushUpData checkPushUpDataAfterDeletion = pushUpRepository.findById(4L).orElse(null);
        assertNull(checkPushUpDataAfterDeletion, "PushUp Data Deleted");
    }
}










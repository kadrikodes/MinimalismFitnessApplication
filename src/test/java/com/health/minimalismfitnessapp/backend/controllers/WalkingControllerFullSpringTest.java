package com.health.minimalismfitnessapp.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.minimalismfitnessapp.TestUtilities;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserGender;
import com.health.minimalismfitnessapp.backend.services.WalkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WalkingController.class)
@AutoConfigureMockMvc
public class WalkingControllerFullSpringTest {
    @MockBean
    WalkingService mockWalkingService;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    TestUtilities testUtilities = new TestUtilities();
    LocalDateTime dateTime = LocalDateTime.of(2023, 11, 10, 12, 30);
    LocalDate birthDate = LocalDate.of(1997, 06, 11);
    UserData userData = new UserData("Kadri", 120, 70, birthDate, UserGender.MALE);

    @Test
    void testGetAllWalkingData() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/walking/allWalkingData");

        mockMvc.perform(requestBuilder).andExpect(status().isOk());

        verify(mockWalkingService, times(1)).findAll();
    }

    @Test
    void testGetAllWalkingDataFromHttpRequest() throws Exception {
        List<WalkingData> walkingData = testUtilities.createWalkingData();

        when(mockWalkingService.findAll()).thenReturn(walkingData);

        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/walking/allWalkingData"));
        resultActions.andExpect(status().isOk());


        verify(mockWalkingService, times(1)).findAll();
    }

    @Test
    void testGetWalkingDataById() throws Exception {
        Long walkingId = 1L;

        WalkingData walkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData);
        when(mockWalkingService.getWalkingDataById(walkingId)).thenReturn(walkingData);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/walking/" + walkingId.toString());
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockWalkingService, times(1)).getWalkingDataById(walkingId);
    }

    @Test
    void testGetWalkingDataByName() throws Exception {
        String name = "Kadri";

        WalkingData walkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData);
        when(mockWalkingService.getWalkingDataByUserName(name)).thenReturn(Collections.singletonList(walkingData));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/walking/name/" + name);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockWalkingService, times(1)).getWalkingDataByUserName(name);
    }

    @Test
    void testAddWalkingData() throws Exception {
        WalkingData walkingData = new WalkingData(1, 10, 100, 60, 5, dateTime, userData);

        String json = mapper.writeValueAsString(walkingData);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/walking/addWalkingData");
        MvcResult result = mockMvc.perform((requestBuilder)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        verify(mockWalkingService, times(1)).addWalkingData(any(WalkingData.class));
    }

    @Test
    void testSearchWalkingData() throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        double distance = 10.0;

        WalkingData expectedResult = new WalkingData(1, 10, 100, 60, 5, dateTime, userData);

        when(mockWalkingService.searchEntriesByCriteria(dateTime, distance)).thenReturn(Collections.singletonList(expectedResult));

        String json = mapper.writeValueAsString(expectedResult);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/walking/search");
        MvcResult result = mockMvc.perform((requestBuilder)
                        .param("dateTime", dateTime.toString())
                        .param("distance", String.valueOf(distance))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        verify(mockWalkingService, times(1)).searchEntriesByCriteria(dateTime, distance);
    }

    @Test
    void testUpdateWalkingData() throws Exception {
        Long walkingId = 1L;
        WalkingData updatedData = new WalkingData(1, 15, 120, 70, 7, dateTime, userData);

        when(mockWalkingService.updateWalkingData(eq(walkingId), any(WalkingData.class))).thenReturn(updatedData);

        String json = mapper.writeValueAsString(updatedData);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/walking/update/" + walkingId.toString());
        MvcResult result = mockMvc.perform((requestBuilder)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        verify(mockWalkingService, times(1)).updateWalkingData(eq(walkingId), any(WalkingData.class));
    }

    @Test
    void testDeleteWalkingData() throws Exception {
        Long walkingId = 1L;

        mockMvc.perform(delete("/walking/delete/" + walkingId.toString()))
                .andExpect(status().isOk());

        verify(mockWalkingService, times(1)).deleteWalkingTracker(walkingId);
    }
}

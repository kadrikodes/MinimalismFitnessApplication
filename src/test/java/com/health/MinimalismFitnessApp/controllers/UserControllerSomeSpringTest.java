package com.health.MinimalismFitnessApp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.health.MinimalismFitnessApp.entities.UserData;
import com.health.MinimalismFitnessApp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerSomeSpringTest {

    @MockBean
    UserService mockUserService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserController userController;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");
        mockMvc.perform(requestBuilder);
        verify(mockUserService, times(1)).findAll();
    }

    @Test
    void addUser() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        UserData userData = new UserData("Esra", 1L, 170.0, 60.0, LocalDate.of(1980, 6, 19), UserData.FEMALE);
        String json = mapper.writeValueAsString(userData);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isCreated())
                        .andReturn();
    }

    @Test
    void updateUser() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        UserData updatedUserData = new UserData("Joe", 1L, 175.0, 65.0, LocalDate.of(1990, 7, 10), UserData.MALE);
        String json = mapper.writeValueAsString(updatedUserData);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteUser() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/users/{userId}", 1))
                .andExpect(status().isOk())
                .andReturn();
}}
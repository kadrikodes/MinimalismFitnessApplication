package com.health.minimalismfitnessapp.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.health.minimalismfitnessapp.backend.entities.UserData;
import com.health.minimalismfitnessapp.backend.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerSpringTest {


    @MockBean
    UserService mockUserService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    final
    ObjectMapper mapper = new ObjectMapper();

    @Test
    void findAll() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");
        mockMvc.perform(requestBuilder);
        verify(mockUserService, times(1)).findAll();
    }

    @Test
    void testGetUserDataById() throws Exception {
        long userId = 1L;

        UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 6, 19), UserData.FEMALE);
        when(mockUserService.getUserById(userId)).thenReturn(userData);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/userId/" + userId);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockUserService, times(1)).getUserById(userId);
    }

    @Test
    void testGetUserDataByName() throws Exception {
        String name = "Esra";

        UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 6, 19), UserData.FEMALE);
        when(mockUserService.getAllUsersByName(name)).thenReturn(Optional.of((userData)));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/name/" + name);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        verify(mockUserService, times(1)).getAllUsersByName(name);
    }

    @Test
    void addUser() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 6, 19), UserData.FEMALE);
        String json = mapper.writeValueAsString(userData);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isCreated())
                        .andReturn();
    }

    @Test
    void updateUser() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        UserData updatedUserData = new UserData("Joe", 175.0, 65.0, LocalDate.of(1990, 7, 10), UserData.MALE);
        String json = mapper.writeValueAsString(updatedUserData);
        mockMvc.perform(MockMvcRequestBuilders.put("/users/updateUser/"+ 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void deleteUser() throws Exception {
        long userId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/deleteUser/"+ 1L))
                .andExpect(status().isOk())
                .andReturn();
        verify(mockUserService, times(1)).deleteUser(userId);

    }}
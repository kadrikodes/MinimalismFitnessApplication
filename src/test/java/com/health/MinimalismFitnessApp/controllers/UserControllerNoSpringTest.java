package com.health.minimalismfitnessapp.controllers;

import com.health.minimalismfitnessapp.entities.UserData;
import com.health.minimalismfitnessapp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerNoSpringTest {

    UserService mockUserService;
    UserController userController;

    @BeforeEach
    void beforeEach() {
        this.mockUserService = mock(UserService.class);
        this.userController = new UserController(mockUserService);
    }

    @Test
    void testGetAllWalkingData() {
        userController.findAll();
        verify(mockUserService, times(1)).findAll();
    }


    @Test
    void testGetUserDataById() {
        long userId = 1L;
        UserData expectedUserData = new UserData("Esra", 1L, 170.0, 160.0, LocalDate.of(1980, 6, 19), "FEMALE");
        when(mockUserService.getUserById(userId)).thenReturn(expectedUserData);
        userController.getUserById(userId);
        verify(mockUserService, times(1)).getUserById(userId);
    }

    @Test
    void testGetWalkingDataByName() {
        String name = "Esra";
        UserData expectedUserData = new UserData("Esra", 1L, 170.0, 160.0, LocalDate.of(1980, 6, 19), "FEMALE");
        when(mockUserService.getAllUsersByName(name)).thenReturn(Optional.of(expectedUserData));
        userController.getUserDataByUserName(name);
        verify(mockUserService, times(1)).getAllUsersByName(name);
    }


    @Test
    void testAddUserData() {
        UserData userData = new UserData("Esra", 0L, 170.0, 60.0, LocalDate.of(1980, 6, 19), "FEMALE");
        when(mockUserService.addUser(userData)).thenReturn(userData);
        UserData addedUserData = userController.addUser(userData);
        verify(mockUserService, times(1)).addUser(userData);
        assertEquals(userData, addedUserData);
    }


    @Test
    void testUpdateUserData() {
        long userId = 1L;
        UserData userData = new UserData("Esra", 0L, 170.0, 60.0, LocalDate.of(1980, 6, 19), "FEMALE");
        when(mockUserService.updateUser(userId, userData)).thenReturn(userData);
        UserData updatedUserData = userController.updateUser(userId, userData);
        verify(mockUserService, times(1)).updateUser(userId, userData);
        assertEquals(userData, updatedUserData);
    }

    @Test
    void testDeleteUserData() {
        long userId = 1L;
        ResponseEntity<String>response=userController.deleteUser(userId);
        verify(mockUserService, times(1)).deleteUser(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User data deleted", response.getBody());
    }

}




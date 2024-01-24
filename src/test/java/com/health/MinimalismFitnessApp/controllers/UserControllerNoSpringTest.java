package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.UserData;
import com.health.MinimalismFitnessApp.services.UserService;
//import org.h2.engine.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


public class UserControllerNoSpringTest {


    UserService mockUserService;
    UserController userController;

    LocalDate expectedLocalDate = LocalDate.of(1980,06,19);
    UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 06, 19), "FEMALE");

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
@Disabled
    @Test
    void testGetUserDataById() {
        Long userId = 1L;
        UserData expectedUserData = new UserData("Esra", 170.0, 160.0, LocalDate.of(1980, 06, 19), "FEMALE");
        when(mockUserService.getUserById(userId)).thenReturn(expectedUserData);
        userController.getUserById(userId);
        verify(mockUserService, times(1)).getUserById(userId);
    }


    @Test
    void testAddUserData() {
        UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 06, 19), "FEMALE");

        when(mockUserService.addUser(userData)).thenReturn(userData);

        UserData addedUserData = userController.addUser(userData);

        verify(mockUserService, times(1)).addUser(userData);

        assertEquals(userData, addedUserData);
    }


    @Test
    void testUpdateUserData() {
        long userId = 1L;
        UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 06, 19), "FEMALE");
        when(mockUserService.updateUser(userId, userData)).thenReturn(userData);
        UserData updatedUserData = userController.updateUser(userId, userData);
        verify(mockUserService, times(1)).updateUser(userId, userData);
        assertEquals(userData, updatedUserData);
    }

//    @Test
//    void testDeleteUserData() {
//        long userId = 1L;
//
//        ResponseEntity<String> response = userController.deleteUser(userId);
//
//        verify(mockUserService, times(1)).deleteUser(userId);
//
//        userService.deleteUser(userId);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Walking data deleted successfully", response.getBody());
//    }

}



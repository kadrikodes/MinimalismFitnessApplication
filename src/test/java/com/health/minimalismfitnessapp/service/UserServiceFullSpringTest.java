package com.health.minimalismfitnessapp.service;

import com.health.minimalismfitnessapp.TestUtilitiesUser;
import com.health.minimalismfitnessapp.backend.MinimalismFitnessAppApplication;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserGender;
import com.health.minimalismfitnessapp.backend.services.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = MinimalismFitnessAppApplication.class)
public class UserServiceFullSpringTest {

    @MockBean
    IUserRepository userRepository;

    @Autowired
    UserService userService;
//    @Autowired
//    TestUtilitiesUser testUtilitiesUser =new TestUtilitiesUser();

    UserData userData1;
    UserData userData2;

    @BeforeEach
    public void populateData() {
        userData1 = new UserData("Rais", 180, 85, LocalDate.of(2000, 1, 1), UserGender.MALE);
        userRepository.save(userData1);

        userData2 = new UserData("Divin", 160, 68, LocalDate.of(1994, 1, 1), UserGender.MALE);
        userRepository.save(userData2);

    }

//    @Test
//    void testFindAllWithSpring() {
//        List<UserData> userData = testUtilitiesUser.createUserData();
//        when(userRepository.findAll()).thenReturn(userData);
//        List<UserData> actualUserData = userService.findAll();
//        assertEquals(userData, actualUserData);
//    }
    @Test
    void testGetUserDataById() {
        long userID = this.userData1.getId();
        UserData expectedUserData = new UserData("Esra", 170.0, 160.0, LocalDate.of(1980, 06, 19), UserGender.FEMALE);
        when(userRepository.findById(userID)).thenReturn(Optional.of(expectedUserData));

        UserData actualUserData = userService.getUserById(userID);
        assertEquals(expectedUserData, actualUserData);
//        verify(userRepository, times(1)).findById(userID);
    }

    @Test
    void testAddingUserData() {
        UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 06, 19), UserGender.FEMALE);
        when(userRepository.save(any(UserData.class))).thenReturn(userData);
        UserData result = userService.addUser(userData);
        LocalDate expectedLocalDateTime = LocalDate.of(1980,06,19);

        assertNotNull(result);
        assertEquals("Esra", result.getName());
        assertEquals(0L, result.getId());
        assertEquals(170.0, result.getHeight());
        assertEquals(60.0, result.getWeight());
        assertEquals("FEMALE", result.getGender());
        assertEquals(expectedLocalDateTime, result.getBirthdate());
        verify(userRepository, times(1)).save(any(UserData.class));
    }

    @Test
    void testUpdateUserData() {
        long userId = 1L;
        UserData updatedUserData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 06, 19), UserGender.FEMALE);
        when(userRepository.findById(userId)).thenReturn(Optional.of(new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 06, 19), UserGender.FEMALE)));
        when(userRepository.save(any(UserData.class))).thenReturn(updatedUserData);
        UserData actualUserData = userService.updateUser(userId, new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 06, 19), UserGender.FEMALE));
        assertEquals(updatedUserData, actualUserData);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(UserData.class));
    }
    @Test
    void testDeleteUserTracker() {
        long userId = 1L;
        UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 06, 19), UserGender.FEMALE);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userData));
        userService.deleteUser(userId);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(userData);
    }
    }



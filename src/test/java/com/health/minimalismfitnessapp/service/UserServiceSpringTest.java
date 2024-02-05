package com.health.minimalismfitnessapp.service;

import com.health.minimalismfitnessapp.TestUtilitiesUser;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserGender;
import com.health.minimalismfitnessapp.backend.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@SuppressWarnings("unused")
@SpringBootTest
public class UserServiceSpringTest {

        @MockBean
        IUserRepository iUserRepository;

        @Autowired
        UserService userService;
        @Autowired
        final
        TestUtilitiesUser testUtilitiesUser = new TestUtilitiesUser();

        @Test
        void testFindAllWithSpring() {
            List<UserData> userData = testUtilitiesUser.createUserData();
            when(iUserRepository.findAll()).thenReturn(userData);
            List<UserData> actualUserData = userService.findAll();
            assertEquals(userData, actualUserData);
        }

        @Test
        void testGetUserDataById() {
            long userId = 1L;
            UserData expectedUserData = new UserData("Esra", 170.0, 160.0, LocalDate.of(1980, 6, 19), UserGender.FEMALE);
            when(iUserRepository.findById(userId)).thenReturn(Optional.of(expectedUserData));

            UserData actualUserData = userService.getUserById(userId);
            assertEquals(expectedUserData, actualUserData);
            verify(iUserRepository, times(1)).findById(userId);
        }

        @Test
        void testAddingUserData() {
            UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 6, 19), UserGender.FEMALE);
            when(iUserRepository.save(any(UserData.class))).thenReturn(userData);
            UserData result = userService.addUser(userData);
            LocalDate expectedLocalDateTime = LocalDate.of(1980, 6, 19);

            assertNotNull(result);
            assertEquals("Esra", result.getName());
            assertEquals(0L, result.getId());
            assertEquals(170.0, result.getHeight());
            assertEquals(60.0, result.getWeight());
            assertEquals("FEMALE", result.getGender());
            assertEquals(expectedLocalDateTime, result.getBirthdate());
            verify(iUserRepository, times(1)).save(any(UserData.class));
        }

        @Test
        void testUpdateUserData() {
            long userId = 1L;
            UserData updatedUserData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 6, 19), UserGender.FEMALE);
            when(iUserRepository.findById(userId)).thenReturn(Optional.of(new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 6, 19), UserGender.FEMALE)));
            when(iUserRepository.save(any(UserData.class))).thenReturn(updatedUserData);
            UserData actualUserData = userService.updateUser(userId, new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 6, 19), UserGender.FEMALE));
            assertEquals(updatedUserData, actualUserData);
            verify(iUserRepository, times(1)).findById(userId);
            verify(iUserRepository, times(1)).save(any(UserData.class));
        }

        @Test
        void testDeleteUserTracker() {
            long userId = 1L;
            UserData userData = new UserData("Esra", 170.0, 60.0, LocalDate.of(1980, 6, 19), UserGender.FEMALE);
            when(iUserRepository.findById(userId)).thenReturn(Optional.of(userData));
            userService.deleteUser(userId);
            verify(iUserRepository, times(1)).findById(userId);
            verify(iUserRepository, times(1)).delete(userData);
        }
    }



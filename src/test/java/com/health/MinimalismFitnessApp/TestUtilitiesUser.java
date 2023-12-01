package com.health.minimalismfitnessapp;

import com.health.minimalismfitnessapp.entities.UserData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestUtilitiesUser {

    public List<UserData> createUserData() {
        List<UserData> userData = new ArrayList<>();

        UserData firstUserData = new UserData("Esra", 1L, 170.0, 160.0, LocalDate.of(1980, 6, 19), "FEMALE");
        userData.add(firstUserData);
        UserData secondUserData = new UserData("Divin", 2L, 170.0, 160.0, LocalDate.of(1980, 6, 19), "MALE");
        userData.add(secondUserData);

        return userData;
    }
}

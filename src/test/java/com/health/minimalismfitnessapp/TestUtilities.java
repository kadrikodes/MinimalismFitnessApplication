package com.health.minimalismfitnessapp;


import com.health.minimalismfitnessapp.backend.entities.ActivityData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;

import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserGender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class TestUtilities {

    public ArrayList<WalkingData> createWalkingData() {
        ActivityData activityData = new ActivityData("Walking");
        ArrayList<WalkingData> walkingData = new ArrayList<>();


        WalkingData firstWalkingData = new WalkingData(1, 10, 100, 60, 5, LocalDateTime.of(2023, 11, 10, 12, 30), new UserData("Kadri", 170, 70, LocalDate.of(1997, 06, 11), UserGender.MALE), activityData);
        walkingData.add(firstWalkingData);
        WalkingData secondWalkingData = new WalkingData(100, 50, 500, 120, 10, LocalDateTime.of(2023, 11, 10, 12, 30), new UserData("Salah", 160, 80, LocalDate.of(1992, 06, 15), UserGender.MALE), activityData);
        walkingData.add(secondWalkingData);
        WalkingData thirdWalkingData = new WalkingData(1000, 100, 1000, 240, 15, LocalDateTime.of(2023, 11, 10, 12, 30), new UserData("Amaarae", 150, 75, LocalDate.of(1994, 07, 4), UserGender.FEMALE), activityData);
        walkingData.add(thirdWalkingData);

        return walkingData;
    }
}

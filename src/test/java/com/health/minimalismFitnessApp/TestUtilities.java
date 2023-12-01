package com.health.minimalismFitnessApp;

import com.health.minimalismFitnessApp.entities.UserData;
import com.health.minimalismFitnessApp.entities.WalkingData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class TestUtilities {

    public ArrayList<WalkingData> createWalkingData() {
        ArrayList<WalkingData> walkingData = new ArrayList<>();

        WalkingData firstWalkingData = new WalkingData(1, 10, 100, 60, 5, LocalDateTime.of(2023, 11, 10, 12, 30), new UserData("Kadri", 1L, 170, 70, LocalDate.of(1997, 06, 11), "Male"));
        walkingData.add(firstWalkingData);
        WalkingData secondWalkingData = new WalkingData(100, 50, 500, 120, 10, LocalDateTime.of(2023, 11, 10, 12, 30), new UserData("Salah", 2L, 160, 80, LocalDate.of(1992, 06, 15), "Male"));
        walkingData.add(secondWalkingData);
        WalkingData thirdWalkingData = new WalkingData(1000, 100, 1000, 240, 15, LocalDateTime.of(2023, 11, 10, 12, 30), new UserData("Amaarae", 3L, 150, 75, LocalDate.of(1994, 07, 4), "Female"));
        walkingData.add(thirdWalkingData);

        return walkingData;
    }
}

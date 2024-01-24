package com.health.MinimalismFitnessApp;

import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class TestUtilitiesUser {

    public List<UserData> createUserData() {
        List<UserData> userData = new ArrayList<>();

        UserData firstUserData = new UserData("Esra", 170.0, 160.0, LocalDate.of(1980, 06, 19), "FEMALE");
        userData.add(firstUserData);
        UserData secondUserData = new UserData("Divin", 170.0, 160.0, LocalDate.of(1980, 06, 19), "MALE");
        userData.add(secondUserData);

        return userData;
    }
    public static <T> long getSize(Iterable<T> iterable){
        return StreamSupport.stream(iterable.spliterator(),false).count();
    }
}

package com.health.minimalismfitnessapp;

import com.health.minimalismfitnessapp.entities.UserData;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD:src/test/java/com/health/MinimalismFitnessApp/TestUtilitiesUser.java
=======
import java.util.stream.StreamSupport;
>>>>>>> master:src/test/java/com/health/minimalismfitnessapp/TestUtilitiesUser.java

@Component
public class TestUtilitiesUser {

    public List<UserData> createUserData() {
        List<UserData> userData = new ArrayList<>();

<<<<<<< HEAD:src/test/java/com/health/MinimalismFitnessApp/TestUtilitiesUser.java
        UserData firstUserData = new UserData("Esra", 1L, 170.0, 160.0, LocalDate.of(1980, 6, 19), "FEMALE");
        userData.add(firstUserData);
        UserData secondUserData = new UserData("Divin", 2L, 170.0, 160.0, LocalDate.of(1980, 6, 19), "MALE");
=======
        UserData firstUserData = new UserData("Esra", 1L, 170.0, 160.0, LocalDate.of(1980, 06, 19), "FEMALE");
        userData.add(firstUserData);
        UserData secondUserData = new UserData("Divin", 2L, 170.0, 160.0, LocalDate.of(1980, 06, 19), "MALE");
>>>>>>> master:src/test/java/com/health/minimalismfitnessapp/TestUtilitiesUser.java
        userData.add(secondUserData);

        return userData;
    }
<<<<<<< HEAD:src/test/java/com/health/MinimalismFitnessApp/TestUtilitiesUser.java
=======
    public static <T> long getSize(Iterable<T> iterable){
        return StreamSupport.stream(iterable.spliterator(),false).count();
    }
>>>>>>> master:src/test/java/com/health/minimalismfitnessapp/TestUtilitiesUser.java
}

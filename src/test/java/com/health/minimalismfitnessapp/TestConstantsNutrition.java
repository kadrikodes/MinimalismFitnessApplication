package com.health.minimalismfitnessapp;

import com.health.minimalismfitnessapp.entities.NutritionData;
import com.health.minimalismfitnessapp.entities.UserData;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestConstantsNutrition {

    public static final String EXPECTED_ALL_NUTRITION_JSON = """
            [{"id":1000,"foodName":"Pizza","calories":500,"protein":40,"carbohydrates":30,"fats":30,"mealType":"Lunch","user":{"id":1,"name":"Rais","height":180.0,"weight":85.0,"birthdate":"2000-01-01","gender":"MALE"}},{"id":2000,"foodName":"Burger","calories":600,"protein":60,"carbohydrates":20,"fats":20,"mealType":"Dinner","user":{"id":2,"name":"Divin","height":160.0,"weight":68.0,"birthdate":"1999-01-01","gender":"MALE"}}]""";

    public static final String EXPECTED_ONE_NUTRITION_JSON = """
            {"id":1000,"foodName":"Pizza","calories":500,"protein":40,"carbohydrates":30,"fats":30,"mealType":"Lunch","user":{"id":1,"name":"Rais","height":180.0,"weight":85.0,"birthdate":"2000-01-01","gender":"MALE"}}""";

    public static ArrayList<NutritionData> getNutritionList() {
        ArrayList<NutritionData> nutritionDataArrayList = new ArrayList<>();

        NutritionData nutritionData1 = new NutritionData("Pounded Yam", 600, 20, 60, 20, "Dinner", new UserData("Rais", 180, 85, LocalDate.of(2000,1,1), "MALE"));
        nutritionDataArrayList.add(nutritionData1);
        NutritionData nutritionData2 = new NutritionData("Curry", 500, 30, 30, 40, "Lunch", new UserData("Kadri", 185, 90, LocalDate.of(1997,1,1), "MALE"));
        nutritionDataArrayList.add(nutritionData2);
        NutritionData nutritionData3 = new NutritionData("Fish and chips", 400, 30, 40, 30, "Lunch", new UserData("Esra", 160, 60, LocalDate.of(1998,1,1), "FEMALE"));
        nutritionDataArrayList.add(nutritionData3);

        return nutritionDataArrayList;
    }

}

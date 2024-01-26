package com.health.minimalismfitnessapp.backend.entities;

import jakarta.persistence.*;

@Entity
public class NutritionData {
    @Id
    @GeneratedValue
    private Long id;

    private String foodName;
    private int calories;
    private int protein;
    private int carbohydrates;
    private int fats;
    private String mealType;

    @ManyToOne
    private UserData userData;

    public NutritionData(){}

    public NutritionData(String foodName, int calories, int protein, int carbohydrates, int fats, String mealType, UserData userData) {
        this.foodName = foodName;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.mealType = mealType;
        this.userData = userData;
    }

    public Long getId() {
        return id;
    }
  
    public void setId(Long id){ this.id = id;}

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public UserData getUser() {
        return userData;
    }

    public void setUser(UserData userData) {
        this.userData = userData;
    }
}

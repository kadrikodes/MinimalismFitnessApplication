package com.health.MinimalismFitnessApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class NutritionTracker {
    @Id
    @GeneratedValue
    private Long id;

    private String foodName;
    private int calories;
    private int protein;
    private int carbohydrates;
    private int fats;
    private String mealType;
    private User user;







}

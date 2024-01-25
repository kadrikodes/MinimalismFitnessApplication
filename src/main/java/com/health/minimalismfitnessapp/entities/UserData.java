package com.health.minimalismfitnessapp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class UserData {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double height;
    private double weight;
    private LocalDate birthdate;
    private String gender;
    public static final String MALE="MALE";
    public static final String FEMALE="FEMALE";


<<<<<<< HEAD:src/main/java/com/health/MinimalismFitnessApp/entities/UserData.java
    //@OneToMany //TODO : add this mapping + ArrayList

=======
>>>>>>> master:src/main/java/com/health/minimalismfitnessapp/entities/UserData.java
    public UserData(){}

    public UserData(String name, double height, double weight, LocalDate birthdate, String gender) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}

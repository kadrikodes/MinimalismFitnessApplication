package com.health.MinimalismFitnessApp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class UserData {
    @Id
    @GeneratedValue(generator = "message_sequence")
    @SequenceGenerator(name="message_sequence", initialValue = 100)
    private Long id;

    private String name;
    private double height;
    private double weight;
    private LocalDate birthdate;
    private String gender;
public static final String MALE="MALE";
public static final String FEMALE="FEMALE";




    //@OneToMany //TODO : add this mapping + ArrayList

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

    public void setId(Long id) {
        this.id = id;
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

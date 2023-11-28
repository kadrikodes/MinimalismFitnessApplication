package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.UserData;
import com.health.MinimalismFitnessApp.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService= userService;
    }

    @PostMapping //TODO Implement the post mapping at a later stage
    @ResponseStatus(HttpStatus.CREATED)
    public UserData addUser(@RequestBody UserData userData) {
        UserData newUser;
        try {
            newUser = this.userService.addUser(userData);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return newUser;
    }


    @GetMapping("/user")
    public Iterable<UserData> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user/{userId}")
    public UserData getUserById(@PathVariable long userId) {
        UserData userData = userService.getUserById(userId);
        if(userData == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return  userData;
    }

}



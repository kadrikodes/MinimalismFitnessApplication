package com.health.minimalismfitnessapp.controllers;

import com.health.minimalismfitnessapp.entities.UserData;
import com.health.minimalismfitnessapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserData addUser(@RequestBody UserData userData) {
        UserData newUser;
        try {
            newUser=this.userService.addUser(userData);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return newUser;
    }


    @GetMapping("")
    public Iterable<UserData> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserData getUserById(@PathVariable long userId) {
        return userService.findByID(userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserData updateUser(@PathVariable Long userId, @RequestBody UserData updatedUserData) {
        try {
            userService.updateUser(userId, updatedUserData);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
        return updatedUserData;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        try {
            userService.deleteUser(userId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }
}



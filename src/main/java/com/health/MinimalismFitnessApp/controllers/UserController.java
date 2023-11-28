package com.health.MinimalismFitnessApp.controllers;

import com.health.MinimalismFitnessApp.entities.UserData;
import com.health.MinimalismFitnessApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseStatusException addUser(@RequestBody UserData userData) {
        ResponseStatusException newUser;
        try {
            newUser=this.userService.addUser(userData);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return newUser;
    }


    @GetMapping("/user")
    public Iterable<UserData> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserData getAllUsers() {
        List<UserData> users = userService.getAllUsers();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else {
            throw new ResponseStatusException(HttpStatus.OK, "User not found");
        }
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserData updateUser(@PathVariable long userId, @RequestBody UserData updatedUserData) {
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



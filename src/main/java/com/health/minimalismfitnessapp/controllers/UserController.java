package com.health.minimalismfitnessapp.controllers;

import com.health.minimalismfitnessapp.entities.UserData;
import com.health.minimalismfitnessapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> master
@RestController
@RequestMapping("/users")
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
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


    @GetMapping
    public Iterable<UserData> findAll() {
        return userService.findAll();
    }

    @GetMapping("/userId/{userId}")
    public UserData getUserById(@PathVariable long userId) {
        UserData userData = userService.getUserById(userId);
        if (userData==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + " has not been found.");
            //return userService.findByID(userId);
        }return userData;
    }


    @GetMapping("/name/{name}")
    public Optional<UserData> getUserDataByUserName(@PathVariable String name) {
        return userService.getAllUsersByName(name);
    }

    @PutMapping("/updateUser/{userId}")
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

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId) {
        try {
            userService.deleteUser(userId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }return ResponseEntity.ok("User data deleted");
    }
}



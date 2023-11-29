package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository mockIUserRepository) {
        this.iUserRepository=mockIUserRepository;
    }

    public List<UserData> users=new ArrayList<>();

    public List<UserData> findAll() {
        return this.iUserRepository.findAll();
    }

    public Optional<UserData> getAllUsersByName(String name) {return iUserRepository.findUserDataByName(name);}

    public UserData addUser(UserData userData){
        if(userData.getId() != null && userData.getId() != 0)
            throw  new IllegalArgumentException("The ID provided for a create/post must be null or zero.");
        return this.iUserRepository.save(userData);
    }

    public UserData getUserById(long userId) {
        Optional<UserData> optionalUser=this.iUserRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + " has not been found.");
        }else {
            UserData userData = optionalUser.get();
        throw new ResponseStatusException(HttpStatus.OK, "User with ID " + userId + " has been successfully found.");
    }}

    public UserData updateUser(long userId, UserData updatedUserData) {
        Optional<UserData> optionalUser=this.iUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (optionalUser.isPresent()){
            UserData existingUser = optionalUser.get();
            existingUser.setName(updatedUserData.getName());
            existingUser.setHeight(updatedUserData.getHeight());
            existingUser.setWeight(updatedUserData.getWeight());
            existingUser.setBirthdate(updatedUserData.getBirthdate());
            existingUser.setGender(updatedUserData.getGender());
            return iUserRepository.save(existingUser);
        }else {
            throw new ResponseStatusException(HttpStatus.OK, "User with ID " + userId + " has been successfully updated.");
        }

    }

    public void deleteUser(long userId) {
        Optional<UserData> optionalUser=this.iUserRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with ID " + userId + " has not been found.");
        } else if (optionalUser.isPresent()) {
            UserData existingUser = optionalUser.get();
            this.iUserRepository.delete(existingUser);

        }else {

        throw new ResponseStatusException(HttpStatus.OK, "User with ID " + userId + " has been successfully deleted.");
    }}

}

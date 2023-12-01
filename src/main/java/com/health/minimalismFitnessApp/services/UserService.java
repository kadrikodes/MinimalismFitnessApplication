package com.health.minimalismFitnessApp.services;

import com.health.minimalismFitnessApp.dataaccess.IUserRepository;
import com.health.minimalismFitnessApp.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository mockIUserRepository) {
        this.iUserRepository=mockIUserRepository;
    }

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
        return optionalUser.orElse(null);
    }

    public UserData updateUser(long userId, UserData updatedUserData) {
        Optional<UserData> optionalUser=this.iUserRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            UserData existingUser = optionalUser.get();
            existingUser.setName(updatedUserData.getName());
            existingUser.setHeight(updatedUserData.getHeight());
            existingUser.setWeight(updatedUserData.getWeight());
            existingUser.setBirthdate(updatedUserData.getBirthdate());
            existingUser.setGender(updatedUserData.getGender());
            return iUserRepository.save(existingUser);
        }

    }

    public void deleteUser(long userId) {
        Optional<UserData> optionalUser=this.iUserRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with ID " + userId + " has not been found.");
        } else {
            UserData existingUser = optionalUser.get();
            this.iUserRepository.delete(existingUser);

        }
    }
}

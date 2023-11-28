package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository mockIUserRepository) {
        this.iUserRepository=mockIUserRepository;
    }

    public UserData addUser(UserData userData){
        if(userData.getId() != null && userData.getId() != 0)
            throw  new IllegalArgumentException("The ID provided for a create/post must be null or zero.");
        return this.iUserRepository.save(userData);
    }

    public List<UserData> findAll() {
        return this.iUserRepository.findAll();
    }

    public UserData getUserById(long userId) {
        Optional<UserData> optionalUser=this.iUserRepository.findById(userId);
        if(optionalUser.isEmpty()){
            return null;
        }else return optionalUser.get();
    }

}

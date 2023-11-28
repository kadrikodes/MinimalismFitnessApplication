package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.User;
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

    public User addUser(User user){
        if(user.getId() != null && user.getId() != 0)
            throw  new IllegalArgumentException("The ID provided for a create/post must be null or zero.");
        return this.iUserRepository.save(user);
    }

    public List<User> findAll() {
        return this.iUserRepository.findAll();
    }

    public User getUserById(long userId) {
        Optional<User> optionalUser=this.iUserRepository.findById(userId);
        if(optionalUser.isEmpty()){
            return null;
        }else return optionalUser.get();
    }

}

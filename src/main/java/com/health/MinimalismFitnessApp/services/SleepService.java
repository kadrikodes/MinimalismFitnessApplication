package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.ISleepRepository;
import com.health.MinimalismFitnessApp.dataaccess.IUserRepository;
import com.health.MinimalismFitnessApp.entities.SleepData;
import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SleepService {
    public static final String SENDER_MUST_EXIST = "The sender of the message must already exist.";
    public static final String MESSAGE_ID_MUST_BE_NULL_OR_0 = "The message id provided for a create/post must be null or zero.";

    ISleepRepository sleepRepository;
    IUserRepository userRepository;
    @Autowired
    public SleepService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserData> findAll() {
        return this.userRepository.findAll();
    }

    public SleepData getSleepRecordById(long sleepId){
        Optional<SleepData> sleepTracker = sleepRepository.findById(sleepId);
        return sleepTracker.orElse(null);
    }

    public List<SleepData> getSleepRecordByName(String name) {
        return sleepRepository.findSleepRecordByName(name);
    }






}

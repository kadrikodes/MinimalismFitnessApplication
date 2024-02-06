package com.health.minimalismfitnessapp.backend.services;

import com.health.minimalismfitnessapp.backend.dataaccess.IPushUpRepository;
import com.health.minimalismfitnessapp.backend.dataaccess.IUserRepository;
import com.health.minimalismfitnessapp.backend.entities.PushUpData;
import com.health.minimalismfitnessapp.backend.entities.WalkingData;
import com.health.minimalismfitnessapp.backend.entities.userdata.UserData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PushUpService {
    IPushUpRepository pushUpRepository;
    IUserRepository userRepository;

    @Autowired
    public PushUpService(IPushUpRepository pushUpRepository, IUserRepository userRepository) {

        this.pushUpRepository = pushUpRepository;
        this.userRepository = userRepository;
    }

    public List<PushUpData> findAll() {
        return pushUpRepository.findAll();
    }

    public PushUpData getPushUpDataById(long pushUpId) {
        Optional<PushUpData> pushUpData = pushUpRepository.findById(pushUpId);
        return pushUpData.orElse(null);
    }

    public List<PushUpData> getPushUpDataByUserDataName(String name) {
        return pushUpRepository.findPushUpDataByUserDataName(name);
    }

    public PushUpData addPushUpData(PushUpData pushUpData) {
        String name = pushUpData.getUser().getName();
        Optional<UserData> prospectiveUser = userRepository.findUserDataByName(name);
        if(prospectiveUser.isEmpty()){
            UserData userData = userRepository.save(pushUpData.getUser());
            pushUpData.setUser(userData);
        } else{
            pushUpData.setUser(prospectiveUser.get());
        }

        return pushUpRepository.save(pushUpData);
    }

    public PushUpData updatePushUpData(long id, PushUpData pushUpData) {
        PushUpData existingPushUpData = pushUpRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Push up data not found"));

        existingPushUpData.setNumberOfPushUps(pushUpData.getNumberOfPushUps());
        existingPushUpData.setTarget(pushUpData.getTarget());
        existingPushUpData.setTimeDuration(pushUpData.getTimeDuration());
        existingPushUpData.setCaloriesBurnt(pushUpData.getCaloriesBurnt());
        existingPushUpData.setUser(pushUpData.getUser());
        existingPushUpData.setActivityData(pushUpData.getActivityData());

        pushUpRepository.save(existingPushUpData);
        return existingPushUpData;
    }

    public void deletePushUpData(long id) {
        PushUpData pushUpData = pushUpRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pushup data not found"));

        pushUpRepository.delete(pushUpData);
    }
}

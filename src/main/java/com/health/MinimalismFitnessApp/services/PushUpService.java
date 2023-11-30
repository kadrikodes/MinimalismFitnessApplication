package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IPushUpRepository;
import com.health.MinimalismFitnessApp.entities.PushUpData;
import com.health.MinimalismFitnessApp.entities.WalkingData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PushUpService {

    static IPushUpRepository pushUpRepository;

    @Autowired
    public PushUpService(IPushUpRepository pushUpRepository) {
        this.pushUpRepository = pushUpRepository;
    }

    public static void delete(long delete) {
        pushUpRepository.deleteById(delete);
    }

    public static PushUpData saveOrUpdate(PushUpData pushUpData) {
        pushUpRepository.save(pushUpData);
        return pushUpData;
    }

    public List<PushUpData> findAll() {
        return pushUpRepository.findAll();
    }

    public PushUpData getPushUpDataById(long pushUpDataId) {
        Optional<PushUpData> pushUpData = pushUpRepository.findById(pushUpDataId);
        return pushUpData.orElse(null);
    }

    public List<PushUpData> getPushUpDataByUserDataName(String name) {
        return pushUpRepository.findPushUpDataByUserDataName(name);
    }

    public PushUpData addPushUpData(PushUpData pushUpData) {
        return pushUpData;
    }

    public PushUpData updatePushUpData(long id, PushUpData pushUpData) {
        PushUpData existingPushUpData = pushUpRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Push up data not found"));

        existingPushUpData.setNumberOfPushUps(pushUpData.getNumberOfPushUps());
        existingPushUpData.setTarget(pushUpData.getTarget());
        existingPushUpData.setTimeDuration(pushUpData.getTimeDuration());
        existingPushUpData.setCaloriesBurnt(pushUpData.getCaloriesBurnt());

        return pushUpRepository.save(existingPushUpData);
    }

    public void deletePushUpData(long id) {
        PushUpData pushUpData = pushUpRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Push up data not found"));

//        pushUpData.delete(pushUpData);
    }
}

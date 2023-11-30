package com.health.MinimalismFitnessApp.services;

import com.health.MinimalismFitnessApp.dataaccess.IPushUpRepository;
import com.health.MinimalismFitnessApp.entities.PushUpData;
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

    public static void delete(long pushUpId) {
        pushUpRepository.deleteById(pushUpId);
    }

    public static PushUpData saveOrUpdate(PushUpData pushUpData) {
        pushUpRepository.save(pushUpData);
        return pushUpData;
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
        return pushUpData;
    }

//    public void updatePushUpData(PushUpData pushUpData, long id) {
//        pushUpRepository.save(pushUpData);
//    }
}

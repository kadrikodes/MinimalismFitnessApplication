package com.health.minimalismfitnessapp.backend.dataaccess;


import com.health.minimalismfitnessapp.backend.entities.PushUpData;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPushUpRepository extends ListCrudRepository<PushUpData, Long> {
    List<PushUpData> findPushUpDataByUserDataName(String name);
}

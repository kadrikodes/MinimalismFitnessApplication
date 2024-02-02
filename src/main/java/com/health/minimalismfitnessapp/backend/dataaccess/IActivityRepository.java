package com.health.minimalismfitnessapp.backend.dataaccess;

import com.health.minimalismfitnessapp.backend.entities.ActivityData;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IActivityRepository extends ListCrudRepository<ActivityData, Long> {
//    List<ActivityData> findActivityDataByUserDataName(String name);
}

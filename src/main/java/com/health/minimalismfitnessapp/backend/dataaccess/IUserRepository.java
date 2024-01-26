package com.health.minimalismfitnessapp.backend.dataaccess;

import com.health.minimalismfitnessapp.backend.entities.UserData;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;


@Repository
public interface IUserRepository extends ListCrudRepository<UserData,Long> {
Optional<UserData> findUserDataByName(String name);
}

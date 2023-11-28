package com.health.MinimalismFitnessApp.dataaccess;

import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;


@Repository
public interface IUserRepository extends ListCrudRepository<UserData,Long> {
List<UserData> findUserDataByName(String name);
}

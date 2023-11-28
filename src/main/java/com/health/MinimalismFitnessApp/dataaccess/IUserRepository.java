package com.health.MinimalismFitnessApp.dataaccess;

import com.health.MinimalismFitnessApp.entities.UserData;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;


@Repository
public interface IUserRepository extends ListCrudRepository<UserData,Long> {

}

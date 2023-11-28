package com.health.MinimalismFitnessApp.dataaccess;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.ListCrudRepository;
import com.health.MinimalismFitnessApp.entities.User;


@Repository
public interface IUserRepository extends ListCrudRepository<User,Long> {

}

package com.repositories;

import com.entetiies.ORM.Car;
import com.entetiies.ORM.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Set<Car> getAllBy();

    Set<Car> getAllByUser(User user);

    @Modifying
    void deleteByUserAndId(User user, long id);

    Set<Car> getAllByMake(String make);

    int countAllBy();
}

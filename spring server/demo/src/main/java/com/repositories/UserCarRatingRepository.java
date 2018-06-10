package com.repositories;

import com.entetiies.ORM.Car;
import com.entetiies.ORM.User;
import com.entetiies.ORM.UserCarRating;
import com.entetiies.ORM.enums.Rating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserCarRatingRepository extends CrudRepository<UserCarRating, Long> {

    UserCarRating findByCarIdAndUserId(Car car, User user);

    @Modifying
    @Query("update user_car_rating set rating= :rating where userId = :userId and carId = :carId")
    int updateUserRating(@Param("rating") Rating rating, @Param("userId") User userId, @Param("carId") Car carId);

    Set<UserCarRating> getAllByCarId(Car car);

    Set<UserCarRating> getAllByUserIdAndCarId(User user, Car car);

    @Modifying
    @Query("delete from user_car_rating as ucr where ucr.carId.id= :carId")
    void deleteCarRating(@Param("carId") Long carId);
}


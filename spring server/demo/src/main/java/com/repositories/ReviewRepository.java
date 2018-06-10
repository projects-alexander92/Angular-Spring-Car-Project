package com.repositories;

import com.entetiies.ORM.Car;
import com.entetiies.ORM.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Set<Review> findAllByCar_Id(long id);

    @Modifying
    @Query("delete from reviews as r where r.car.id = :id")
    void deleteReviewByCardId(@Param("id") long id);
}

package com.entetiies.ORM;

import com.entetiies.ORM.enums.Rating;

import javax.persistence.*;

@Entity(name = "user_car_rating")
public class UserCarRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car carId;
    @Enumerated(EnumType.ORDINAL)
    private Rating rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Car getCarId() {
        return carId;
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}

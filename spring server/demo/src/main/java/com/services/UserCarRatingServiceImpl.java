package com.services;

import com.entetiies.ORM.Car;
import com.entetiies.ORM.User;
import com.entetiies.ORM.UserCarRating;
import com.entetiies.ORM.enums.Rating;
import com.entetiies.models.bindingModels.RatingBindingModel;
import com.repositories.CarRepository;
import com.repositories.UserCarRatingRepository;
import com.repositories.UserRepository;
import com.services.interfaces.UserCarRatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCarRatingServiceImpl implements UserCarRatingService
{
    private final UserCarRatingRepository userCarRatingRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public UserCarRatingServiceImpl(UserCarRatingRepository userCarRatingRepository, UserRepository userRepository, CarRepository carRepository) {
        this.userCarRatingRepository = userCarRatingRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public void addRating(RatingBindingModel ratingBindingModel) {
        User user = this.userRepository.findByUsername(ratingBindingModel.getUsername());
        Car car = this.carRepository.findOne(ratingBindingModel.getCarId());
        UserCarRating userCarRating = new UserCarRating();
        Rating rating = Rating.values()[ratingBindingModel.getRating() - 1];
        if (this.userCarRatingRepository.findByCarIdAndUserId(car, user) == null) {
            userCarRating.setCarId(car);
            userCarRating.setUserId(user);
            userCarRating.setRating(rating);
            this.userCarRatingRepository.save(userCarRating);
        } else {
            this.userCarRatingRepository.updateUserRating(rating, user, car);
        }
    }

    @Override
    public double getAverageRating(long carId) {
        Car car = this.carRepository.findOne(carId);
        return this.userCarRatingRepository.getAllByCarId(car).stream().mapToInt(e -> e.getRating().getValue()).average().orElse(0.0);
    }

    @Override
    public double checkIfUserRated(Long carId, String username) {
        Car car = this.carRepository.findOne(carId);
        User user = this.userRepository.findByUsername(username);
        UserCarRating byCarIdAndUserId = this.userCarRatingRepository.findByCarIdAndUserId(car, user);
        if (byCarIdAndUserId == null) {
            return 0.0;
        }
        return byCarIdAndUserId.getRating().getValue();
    }
}

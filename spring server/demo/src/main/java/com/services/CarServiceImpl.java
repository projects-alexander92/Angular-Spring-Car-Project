package com.services;

import com.entetiies.ORM.Car;
import com.entetiies.ORM.User;
import com.entetiies.models.bindingModels.CarBindingModel;
import com.entetiies.models.viewModels.CarDetailsViewModel;
import com.entetiies.models.viewModels.CarForPorfilePageViewModel;
import com.entetiies.models.viewModels.CarTableViewModel;
import com.entetiies.models.viewModels.ReviewViewModel;
import com.repositories.CarRepository;
import com.repositories.ReviewRepository;
import com.repositories.UserCarRatingRepository;
import com.repositories.UserRepository;
import com.services.interfaces.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService
{
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final UserCarRatingRepository userCarRatingRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository,
                          UserRepository userRepository,
                          ReviewRepository reviewRepository,
                          UserCarRatingRepository userCarRatingRepository,
                          ModelMapper modelMapper)
    {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.userCarRatingRepository = userCarRatingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addNewCar(CarBindingModel carBindingModel)
    {
        Car car = this.modelMapper.map(carBindingModel, Car.class);
        car.setImage(carBindingModel.getImageUrl());
        car.setUser(this.userRepository.findByUsername(carBindingModel.getUsername()));
        this.carRepository.save(car);
    }

    @Override
    public Set<CarTableViewModel> getAllCars()
    {
        return this.carRepository.getAllBy().stream().map(e ->
        {
            CarTableViewModel map = this.modelMapper.map(e, CarTableViewModel.class);
            map.setUsername(e.getUser().getUsername());
            return map;
        }).collect(Collectors.toSet());
    }

    @Override
    public CarDetailsViewModel getCarDetails(long id)
    {
        CarDetailsViewModel carDetailsViewModel = new CarDetailsViewModel();
        Car car = this.carRepository.findOne(id);
        carDetailsViewModel = this.modelMapper.map(car, CarDetailsViewModel.class);
        carDetailsViewModel.setUsername(car.getUser().getUsername());
        Set<ReviewViewModel> reviewViewModels = this.reviewRepository.findAllByCar_Id(id).stream().map(e ->
        {
            ReviewViewModel reviewViewModel = this.modelMapper.map(e, ReviewViewModel.class);
            reviewViewModel.setUsername(e.getUser().getUsername());
            return reviewViewModel;
        }).collect(Collectors.toSet());
        carDetailsViewModel.setReviews(reviewViewModels);
        return carDetailsViewModel;
    }

    @Override
    public Set<CarForPorfilePageViewModel> getAllCarsByUsername(String username)
    {
        User user = this.userRepository.findByUsername(username);
        return this.carRepository.getAllByUser(user).stream().map(e -> this.modelMapper.map(e, CarForPorfilePageViewModel.class)).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void deleteCar(long id)
    {
        this.reviewRepository.deleteReviewByCardId(id);
        this.userCarRatingRepository.deleteCarRating(id);
        this.carRepository.delete(id);
    }

    @Override
    public Set<CarTableViewModel> getCarsByMake(String make)
    {
        return this.carRepository.getAllByMake(make).stream().map(e ->
        {
            CarTableViewModel map = this.modelMapper.map(e, CarTableViewModel.class);
            map.setUsername(e.getUser().getUsername());
            return map;
        }).collect(Collectors.toSet());
    }

    @Override
    public int[] userAndCarCount()
    {
        int carCount = this.carRepository.countAllBy();
        int userCount = this.userRepository.countAllBy();
        return new int[]{carCount, userCount};
    }
}

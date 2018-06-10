package com.services.interfaces;

import com.entetiies.models.bindingModels.CarBindingModel;
import com.entetiies.models.viewModels.CarDetailsViewModel;
import com.entetiies.models.viewModels.CarForPorfilePageViewModel;
import com.entetiies.models.viewModels.CarTableViewModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface CarService
{
    void addNewCar(CarBindingModel carBindingModel);

    Set<CarTableViewModel> getAllCars();

    CarDetailsViewModel getCarDetails(long id);

    Set<CarForPorfilePageViewModel> getAllCarsByUsername(String username);

    @Transactional
    void deleteCar(long id);

    Set<CarTableViewModel> getCarsByMake(String make);

    int[] userAndCarCount();
}

package com.controllers;

import com.entetiies.models.bindingModels.CarBindingModel;
import com.entetiies.models.viewModels.CarDetailsViewModel;
import com.entetiies.models.viewModels.CarForPorfilePageViewModel;
import com.entetiies.models.viewModels.CarTableViewModel;
import com.services.interfaces.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarController
{
    private final CarService carService;

    public CarController(CarService carService)
    {
        this.carService = carService;
    }

    @GetMapping("/cars/all")
    public ResponseEntity getAllCars(@RequestParam(required = false, value = "make") String make)
    {
        if (make != null)
        {
            Set<CarTableViewModel> cars = this.carService.getCarsByMake(make);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } else
        {
            Set<CarTableViewModel> cars = this.carService.getAllCars();
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
    }

    @PostMapping("/cars/add")
    public ResponseEntity addNewCar(@Valid @RequestBody CarBindingModel carBindingModel, BindingResult bindingResult, Principal principal)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        carBindingModel.setUsername(principal.getName());
        this.carService.addNewCar(carBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/cars/details/{id}")
    public ResponseEntity getCarrDetails(@PathVariable long id)
    {
        CarDetailsViewModel carDetails = this.carService.getCarDetails(id);
        return new ResponseEntity<>(carDetails, HttpStatus.OK);
    }

    @GetMapping("cars/all/by-username")
    public ResponseEntity getAllCarsByUsername(Principal principal)
    {
        if (principal.getName() == null)
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        Set<CarForPorfilePageViewModel> cars = this.carService.getAllCarsByUsername(principal.getName());
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @DeleteMapping("cars/delete/{id}")
    public ResponseEntity deleteCar(@PathVariable Long id, Principal principal)
    {
        if (id == null || principal.getName() == null)
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        this.carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity getStats()
    {
        int[] ints = this.carService.userAndCarCount();
        return new ResponseEntity<>(ints, HttpStatus.OK);
    }
}

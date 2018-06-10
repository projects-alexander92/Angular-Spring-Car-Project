package com.controllers;

import com.entetiies.models.bindingModels.RatingBindingModel;
import com.services.interfaces.UserCarRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RatingController
{

    private final UserCarRatingService userCarRatingService;

    public RatingController(UserCarRatingService userCarRatingService)
    {
        this.userCarRatingService = userCarRatingService;
    }

    @PostMapping("/rating/add")
    public ResponseEntity submitRating(@Valid @RequestBody RatingBindingModel ratingBindingModel, BindingResult bindingResult, Principal principal)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        ratingBindingModel.setUsername(principal.getName());

        this.userCarRatingService.addRating(ratingBindingModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/rating/average/{id}")
    public ResponseEntity getAverage(@PathVariable Long id)
    {
        if (id == null)
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        double averageRating = this.userCarRatingService.getAverageRating(id);
        return new ResponseEntity<>(averageRating, HttpStatus.OK);
    }

    @GetMapping("/rating/check-for-user/{carId}")
    public ResponseEntity checkIfUserRate(@PathVariable Long carId, Principal principal)
    {
        if (carId == null)
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        double didVote = this.userCarRatingService.checkIfUserRated(carId, principal.getName());
        return new ResponseEntity<>(didVote, HttpStatus.OK);
    }
}

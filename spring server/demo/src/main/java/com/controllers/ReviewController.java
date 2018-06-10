package com.controllers;

import com.entetiies.models.bindingModels.ReviewBindingModel;
import com.services.interfaces.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController
{
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews/add")
    public ResponseEntity addReview(@Valid @RequestBody ReviewBindingModel reviewBindingModel, BindingResult bindingResult, Principal principal)
    {
        if (bindingResult.hasErrors())
        {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        reviewBindingModel.setUsername(principal.getName());
        this.reviewService.saveReview(reviewBindingModel);
        return new ResponseEntity(HttpStatus.OK);
    }
}

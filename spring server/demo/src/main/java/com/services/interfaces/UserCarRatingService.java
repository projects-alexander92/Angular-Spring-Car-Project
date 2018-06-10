package com.services.interfaces;

import com.entetiies.models.bindingModels.RatingBindingModel;
import org.springframework.transaction.annotation.Transactional;

public interface UserCarRatingService
{
    @Transactional
    void addRating(RatingBindingModel ratingBindingModel);

    double getAverageRating(long carId);

    double checkIfUserRated(Long carId, String username);
}

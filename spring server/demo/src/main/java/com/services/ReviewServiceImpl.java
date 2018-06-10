package com.services;

import com.entetiies.ORM.Review;
import com.entetiies.ORM.User;
import com.entetiies.models.bindingModels.ReviewBindingModel;
import com.repositories.ReviewRepository;
import com.repositories.UserRepository;
import com.services.interfaces.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService
{
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired

    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public void saveReview(ReviewBindingModel reviewBindingModel) {
        Review review = this.modelMapper.map(reviewBindingModel, Review.class);
        User user = this.userRepository.findByUsername(reviewBindingModel.getUsername());
        review.setUser(user);
        review.setId(null);
        this.reviewRepository.save(review);
    }
}

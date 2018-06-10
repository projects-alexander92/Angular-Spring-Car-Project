package com.entetiies.models.viewModels;

import com.entetiies.ORM.Review;

import java.math.BigDecimal;
import java.util.Set;

public class CarDetailsViewModel {
    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String engine;
    private BigDecimal price;
    private String image;
    private Double mileage;
    private String username;
    private Set<ReviewViewModel> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<ReviewViewModel> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewViewModel> reviews) {
        this.reviews = reviews;
    }
}

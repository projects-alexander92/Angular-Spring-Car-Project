package com.entetiies.models.bindingModels;

import com.entetiies.ORM.enums.Rating;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewBindingModel {
    @Size(min = 10)
    private String data;
    private String username;
    private long carId;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }
}

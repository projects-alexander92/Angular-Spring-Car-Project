package com.entetiies.models;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationModel {

    @Size(min = 5)
    private String username;
    @Pattern(regexp = "^[\\w]{5,10}$")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

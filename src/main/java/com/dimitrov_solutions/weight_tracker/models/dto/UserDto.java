package com.dimitrov_solutions.weight_tracker.models.dto;

import com.dimitrov_solutions.weight_tracker.models.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class UserDto {
    private String username;
    @Email
    private String email;
    @Min(value = 8, message = "password is too short!")
    @Max(value = 32, message = "password is too long!")
    private String password;
    private String country;
    private String city;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.email = user.getEmail();
    }

    public String getUsername() {
        return username;
    }

    public @Email String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}

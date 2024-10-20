package com.dimitrov_solutions.weight_tracker.models.dto;

import com.dimitrov_solutions.weight_tracker.models.user.entity.User;
import com.dimitrov_solutions.weight_tracker.models.validations.UniqueEmail;
import jakarta.validation.constraints.*;

public class UserDto {
    @NotEmpty
    private final String username;
    @Email
    @UniqueEmail
    private final String email;
    @Min(value = 8, message = "password is too short!")
    @Max(value = 32, message = "password is too long!")
    private String password;
    @NotEmpty
    private final String country;
    private final String city;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.country = user.getCountry();
        this.city = user.getCity();

    }

    public String getUsername() {
        return username;
    }

    public @Email String getEmail() {
        return email;
    }

    public @Min(value = 8, message = "password is too short!") @Max(value = 32, message = "password is too long!") String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}

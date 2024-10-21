package com.dimitrov_solutions.weight_tracker.models.dto;

import com.dimitrov_solutions.weight_tracker.models.user.entity.User;
import com.dimitrov_solutions.weight_tracker.models.validations.UniqueEmail;
import jakarta.validation.constraints.*;

public class UserDto {
    @Email
    @UniqueEmail
    private String email;
    @Size(min = 8, max = 32)
    private String password;
    @NotEmpty
    private String country;
    private String city;

    public UserDto(User user) {
        this.email = user.getEmail();
        this.country = user.getCountry();
        this.city = user.getCity();

    }

    public UserDto() {
    }

    public @Email String getEmail() {
        return email;
    }

    public @Size(min = 8, max = 32) String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

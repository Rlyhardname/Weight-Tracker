package com.dimitrov_solutions.weight_tracker.models.user.entity;

import com.dimitrov_solutions.weight_tracker.models.dto.UserDto;
import com.dimitrov_solutions.weight_tracker.utils.UUID7;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_account")
public class User {

    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String country;
    private String city;

    public User(UserDto dto) {
        id = String.valueOf(UUID7.generateUuid7());
        username = dto.getUsername();
        email = dto.getEmail();
        password = dto.getPassword();
        country = dto.getCountry();
        city = dto.getCity();
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

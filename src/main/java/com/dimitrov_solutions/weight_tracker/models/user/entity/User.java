package com.dimitrov_solutions.weight_tracker.models.user.entity;

import com.dimitrov_solutions.weight_tracker.models.dto.UserDto;
import com.dimitrov_solutions.weight_tracker.utils.UUID7;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "user_account")
public class User {

    @Id
    private UUID id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;

    public User(UserDto dto) {
        id = UUID7.generateUuid7();
        email = dto.getEmail();
        password = dto.getPassword();
        country = dto.getCountry();
        city = dto.getCity();
    }

    public User() {

    }

    public UUID getId() {
        return id;
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
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

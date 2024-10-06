package com.dimitrov_solutions.weight_tracker.models.weather.dto;

import com.dimitrov_solutions.weight_tracker.models.weather.beans.IconNameStateMachine;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WeatherDetailsDto {
    private IconNameStateMachine iconNameStateMachine;
    private String imgName;
    private final int temp;
    private final int clouds;
    private final String country;
    private final String city;


    public WeatherDetailsDto(Double temp, Double clouds, String weatherState, String country, String city) {
        this.temp = BankerRound(temp, "temp");
        this.clouds = BankerRound(clouds, "clouds");
        this.country = country;
        this.city = city;
        getNameBasedOnCriteria(weatherState);
    }

    public void setIconNameStateMachine(IconNameStateMachine iconNameStateMachine) {
        this.iconNameStateMachine = iconNameStateMachine;
    }

    private void getNameBasedOnCriteria(String weatherState) {
        imgName = iconNameStateMachine.nameBasedOnState(weatherState, clouds);
    }

    private int BankerRound(Double value, String field) {
        if (value == null) {
            return 0;
        }

        var decimal = new BigDecimal(value).setScale(0, RoundingMode.HALF_EVEN);
        return decimal.intValueExact();
    }

    @Override
    public String toString() {
        return "WeatherDetailsDto{" +
                "temperature=" + temp +
                ", cloudiness=" + clouds +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", imgName='" + imgName + '\'' +
                '}';
    }
}

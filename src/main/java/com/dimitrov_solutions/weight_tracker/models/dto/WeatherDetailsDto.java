package com.dimitrov_solutions.weight_tracker.models.dto;

public record WeatherDetailsDto(int temp, int clouds, String imgName, String country, String city) {
}
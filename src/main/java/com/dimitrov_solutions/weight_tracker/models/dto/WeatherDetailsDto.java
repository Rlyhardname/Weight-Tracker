package com.dimitrov_solutions.weight_tracker.models.dto;

/**
 * Used by Jackson deserializer and DetailsDtoMapper to map the response
 * which te server receives from the weather API call to a DTO.
 * The DTO then gets sends back to the client-side frontend via the endpoint requested.
 */
public record WeatherDetailsDto(int temp, int clouds, String imgName, String country, String city) {
}
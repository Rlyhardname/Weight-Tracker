package com.dimitrov_solutions.weight_tracker.models.weather.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

public class WeatherUrlFacade {
    @Value("${apiKey}")
    private static String API_KEY;
    private static final String HOST = "api.openweathermap.org";

    public static String createUrl(String country_code, String town) {
        var uri = UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host(HOST)
                .path("/data/2.5/weather")
                .queryParam("q", town, country_code)
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY)
                .build();

        return uri.toUriString();
    }

}

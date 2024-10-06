package com.dimitrov_solutions.weight_tracker.models.weather.beans;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherStateMap {
    private final Map<String, String> weatherStateMap = new HashMap<>(Map.of(
            "clear sky", "CLEAR_SKY",
            "few clouds", "FEW_CLOUDS",
            "scattered clouds", "SCATTERED_CLOUDS",
            "broken_clouds", "BROKEN_CLOUDS",
            "rain", "RAIN",
            "shower rain", "SHOWER_RAIN",
            "thunder storm", "SHOWER_RAIN",
            "snow", "SNOW",
            "mist", "MIST"
    ));

    public boolean contains(String state) {
        return weatherStateMap.containsKey(state);
    }

    public String getEnumValue(String state) {
        return weatherStateMap.get(state);
    }
}

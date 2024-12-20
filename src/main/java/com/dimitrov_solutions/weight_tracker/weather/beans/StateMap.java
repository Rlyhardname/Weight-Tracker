package com.dimitrov_solutions.weight_tracker.weather.beans;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StateMap {
    private final Map<String, String> weatherStateMap = new HashMap<>(Map.of(
            "01d", "CLEAR_SKY",
            "02d", "FEW_CLOUDS",
            "03d", "SCATTERED_CLOUDS",
            "04d", "BROKEN_CLOUDS",
            "09d", "RAIN",
            "10d", "SHOWER_RAIN",
            "11d", "SHOWER_RAIN",
            "13d", "SNOW",
            "50d", "MIST"
    ));

    public boolean contains(String state) {
        return weatherStateMap.containsKey(state);
    }

    public String getEnumValue(String state) {
        return weatherStateMap.get(state);
    }
}
package com.dimitrov_solutions.weight_tracker.models.weather;

public enum WeatherState {
    CLEAR_SKY("clear sky"),
    FEW_CLOUDS("few clouds"),
    SCATTERED_CLOUDS("scattered clouds"),
    BROKEN_CLOUDS("broken_clouds"),
    RAIN("rain"),
    SHOWER_RAIN("shower rain"),
    THUNDER_STORM("thunder storm"),
    SNOW("snow"),
    MIST("mist");

    private final String weather;

    public String getWeather() {
        return weather;
    }

    WeatherState(String weather) {
        this.weather = weather;
    }
}

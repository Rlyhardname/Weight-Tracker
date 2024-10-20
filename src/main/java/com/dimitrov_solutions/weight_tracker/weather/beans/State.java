package com.dimitrov_solutions.weight_tracker.weather.beans;

public enum State {
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

    State(String weather) {
        this.weather = weather;
    }
}

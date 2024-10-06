package com.dimitrov_solutions.weight_tracker.models.weather.beans;

import com.dimitrov_solutions.weight_tracker.models.weather.WeatherState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IconNameStateMachine {
    public static final String POSSIBLE_API_CHANGE = "Possible API changed! null value for %s field";
    public static final String VALUE_NOT_SUPPORTED = "%s not supported! Add: %s";
    public static Logger logger = LoggerFactory.getLogger(IconNameStateMachine.class);
    private final WeatherStateMap weatherStateMap;

    @Autowired
    public IconNameStateMachine(WeatherStateMap weatherStateMap) {
        this.weatherStateMap = weatherStateMap;
    }

    public String nameBasedOnState(String weatherStateStr, int clouds) {
        if (weatherStateStr == null) {
            // TODO not sure if it's correct place to log about possible API chance
            System.out.println("here 1");
            logger.debug(String.format(POSSIBLE_API_CHANGE, "WeatherState"));
            return "default.png";
        }

        try {
            WeatherState state = null;
            if (weatherStateMap.contains(weatherStateStr)) {
                state = WeatherState.valueOf(weatherStateMap.getEnumValue(weatherStateStr));
            }

            return switch (state) {
                case CLEAR_SKY -> "sunny_weather.png";
                case FEW_CLOUDS -> "sunny_cloudy.png";
                case SCATTERED_CLOUDS -> "cloudy_forecast.png";
                case BROKEN_CLOUDS -> "cloudy_forecast.png";
                case RAIN -> nameBasedOnClouds(clouds, "rain");
                case SHOWER_RAIN -> nameBasedOnClouds(clouds, "showerRain");
                case THUNDER_STORM -> "sunny_rainy_thunder.png";
                case SNOW -> nameBasedOnClouds(clouds, "snow");
                case MIST -> "mist.png";
            };
        } catch (NullPointerException e) {
            logger.debug(String.format(VALUE_NOT_SUPPORTED, "WeatherState", weatherStateStr));
            return "default.png";
        }

    }

    public String nameBasedOnClouds(int clouds, String rainType) {
        try {
            if (clouds < 65) {
                if (rainType.equals("rain"))
                    return "sunny_cloudy_weak_rain.png";

                if (rainType.equals("showerRain")) {
                    return "sunny_cloudy_rain.png";
                }

                return "sunny_cloudy_snowy.png";
            }

            if (rainType.equals("rain"))
                return "cloudy_weak_rain.png";

            if (rainType.equals("showerRain")) {
                return "cloudy_rain.png";
            }

            return "cloudy_snowy.png";
        } catch (NullPointerException e) {
            logger.debug("Should not be getting null values here" + rainType);
            return "cloudy_weak_rain.png";
        }

    }

}

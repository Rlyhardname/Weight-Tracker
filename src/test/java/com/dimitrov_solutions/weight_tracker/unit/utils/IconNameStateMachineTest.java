package com.dimitrov_solutions.weight_tracker.unit.utils;

import com.dimitrov_solutions.weight_tracker.models.weather.WeatherState;
import com.dimitrov_solutions.weight_tracker.models.weather.beans.IconNameStateMachine;
import com.dimitrov_solutions.weight_tracker.models.weather.beans.WeatherStateMap;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IconNameStateMachineTest {

    static WeatherState[] weatherStates;
    static Set<String> namesSet;
    static Set<String> namesSetBasedOnRainTypes;
    static String[] arrayOfRainTypes;
    SecureRandom secureRandom;
    private final IconNameStateMachine iconNameStateMachine;

    @Autowired
    public IconNameStateMachineTest(IconNameStateMachine iconNameStateMachine) {
        this.iconNameStateMachine = iconNameStateMachine;

    }

    @BeforeAll
    static void initStatic() {
        weatherStates = WeatherState.values();
        namesSet = Set.of("sunny_weather.png",
                "sunny_cloudy.png",
                "cloudy_forecast.png",
                "sunny_cloudy_weak_rain.png",
                "sunny_cloudy_rain.png",
                "sunny_cloudy_snowy.png",
                "cloudy_weak_rain.png",
                "cloudy_rain.png",
                "cloudy_snowy.png",
                "sunny_rainy_thunder.png",
                "mist.png");
        namesSetBasedOnRainTypes = Set.of("sunny_cloudy_weak_rain.png",
                "sunny_cloudy_rain.png",
                "sunny_cloudy_snowy.png",
                "cloudy_weak_rain.png",
                "cloudy_rain.png",
                "cloudy_snowy.png");
        arrayOfRainTypes = new String[]{"showerRain",
                "rain",
                "snow"};
    }

    @BeforeEach
    void init() {
        secureRandom = new SecureRandom();
    }

    @Test
    void enum_test() {
        WeatherState state = WeatherState.valueOf("SNOW");
        System.out.println(state == WeatherState.SNOW);
        System.out.println(WeatherState.SNOW.compareTo(WeatherState.SNOW));
        System.out.println();
    }

    @Test
    void based_on_state_illegal_argument_returns_default_string() {
        var expected = "default.png";

        String actual = iconNameStateMachine.nameBasedOnState("illegal_argument", 1);

        assertEquals(expected, actual);
    }

    @Test
    void based_on_state_null_argument_returns_default_string() {
        var expected = "default.png";

        String actual = iconNameStateMachine.nameBasedOnState(null, 1);

        assertEquals(expected, actual);
    }

    @Test
    void returns_valid_image_name() {
        String actual = getName();

        System.out.println(actual);

        assertTrue(namesSet.contains(actual));
    }

    @RepeatedTest(value = 1000, failureThreshold = 1)
    void return_valid_image_name_multiple_times() {
        String actual = getName();
        System.out.println(actual);
        assertTrue(namesSet.contains(actual));
    }

    private String getName() {
        int index = secureRandom.nextInt(0, 9);
        int cloudiness = secureRandom.nextInt(0, 101);
        return iconNameStateMachine.nameBasedOnState(weatherStates[index].getWeather(), cloudiness);
    }

    @Nested
    class IconNameStateMachineProtectedTest extends IconNameStateMachine {

        @Autowired
        public IconNameStateMachineProtectedTest(WeatherStateMap weatherStateMap) {
            super(weatherStateMap);
        }

        @Test
        void return_default_rain_icon_from_null_input() {
            String actual = getName(null);

            assertTrue(namesSetBasedOnRainTypes.contains(actual));
        }


        @Test
        void return_valid_image_name_based_on_rain_type() {
            String actual = getName();

            assertTrue(namesSetBasedOnRainTypes.contains(actual));
        }

        @RepeatedTest(value = 100, failureThreshold = 1)
        void return_valid_image_name_based_on_rain_type_multiple_times() {
            String actual = getName();

            assertTrue(namesSetBasedOnRainTypes.contains(actual));
        }

        private String getName() {
            int index = secureRandom.nextInt(0, 3);
            int clouds = secureRandom.nextInt(0, 101);

            return iconNameStateMachine.nameBasedOnClouds(clouds, arrayOfRainTypes[index]);
        }

        private String getName(String input) {
            int clouds = secureRandom.nextInt(0, 101);

            return iconNameStateMachine.nameBasedOnClouds(clouds, input);
        }

    }


}

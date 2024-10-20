package com.dimitrov_solutions.weight_tracker.unit.utils;

import com.dimitrov_solutions.weight_tracker.weather.beans.IconNameStateMachine;
import com.dimitrov_solutions.weight_tracker.weather.beans.StateMap;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class IconNameStateMachineTest {

    static String[] imageCodes;
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
        imageCodes = new String[]{"01d",
                "02d",
                "03d",
                "04d",
                "09d",
                "10d",
                "11d",
                "13d",
                "50d"};
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
        return iconNameStateMachine.nameBasedOnState(imageCodes[index], cloudiness);
    }

    @Nested
    class IconNameStateMachineProtectedTest extends IconNameStateMachine {

        @Autowired
        public IconNameStateMachineProtectedTest(StateMap stateMap) {
            super(stateMap);
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

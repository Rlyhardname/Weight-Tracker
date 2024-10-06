package com.dimitrov_solutions.weight_tracker.unit.utils;

import com.dimitrov_solutions.weight_tracker.utils.RandomPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RandomPasswordTest {

    String actual;

    @BeforeEach
    void init() {
        actual = RandomPassword.generateSecurePassword();
    }

    @Test
    void generate_password_from_util() {
        var expected = String.class;

        assertNotNull(actual);
        assertEquals(expected, actual.getClass(), "both objects should be from class" + expected);
    }

    @Test
    void generated_password_has_length_of_12() {
        int expected = 12;

        assertEquals(expected, actual.length(), "length should be exactly " + expected);
    }

    @Nested
    public class RandomPasswordProtected extends RandomPassword {

        @RepeatedTest(value = 100, failureThreshold = 1)
        void password_contains_same_symbols() {
            char[] actualCharArray = actual.toCharArray();
            char[] expected = RandomPassword.randomArrange(actualCharArray);

            Arrays.sort(actualCharArray);
            Arrays.sort(expected);
            assertArrayEquals(expected, actualCharArray, "both arrays should be identical");
        }

        /**
         * Test will fail once in 500,000,000 times on average...
         * this happens due to randomArrange producing n! possible
         * arrangements which is our implementation is around 500,000,000
         * or in other words 12!
         */
        @RepeatedTest(value = 100, failureThreshold = 1)
        void password_arrangement_differs_from_starting_password() {
            char[] startingConfiguration = actual.toCharArray();
            char[] afterMutation = RandomPassword.randomArrange(startingConfiguration);

            assertFalse(Arrays.equals(afterMutation, startingConfiguration), "passwords should not match, fails once in 500,000,000 times");
        }

    }

}

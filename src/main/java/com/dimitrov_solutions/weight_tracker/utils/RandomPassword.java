package com.dimitrov_solutions.weight_tracker.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class RandomPassword {

    public static final char[] asciiSpecialChars = {33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96, 123, 124, 125, 126};
    public static final char[] asciiNumbers = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57};
    public static final char[] asciiUpperCase = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    public static final char[] asciiLowerCase = {97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};

    public static String generateSecurePassword() {
        return String.valueOf(randomArrange(oneNum_OneSymbol_OneUpperCaseLetter_OneLowerCaseLetter()));
    }

    protected static char[] randomArrange(char[] arr) {
        char[] result = new char[12];
        SecureRandom rand = new SecureRandom();
        List<Integer> indexes = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        for (int i = 0; i < 12; i++) {
            int index = rand.nextInt(0, indexes.size());
            result[i] = arr[indexes.remove(index)];
        }

        return result;
    }

    private static char[] oneNum_OneSymbol_OneUpperCaseLetter_OneLowerCaseLetter() {
        SecureRandom rand = new SecureRandom();
        char[] password = new char[12];

        password[0] = asciiSpecialChars[rand.nextInt(0, 31)];
        password[1] = asciiNumbers[rand.nextInt(0, 9)];
        password[2] = asciiUpperCase[rand.nextInt(0, 26)];
        for (int i = 3; i < 12; i++) {
            password[i] = asciiLowerCase[rand.nextInt(0, 26)];
        }

        return password;
    }
}
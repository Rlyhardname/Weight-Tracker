package com.dimitrov_solutions.weight_tracker.weather.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class UrlFacade {
    public static Logger logger = LoggerFactory.getLogger(UrlFacade.class);
    private static final String API_KEY;
    private static final String HOST = "api.openweathermap.org";

    static {
        String fileUrl = "src/main/resources/api_key.txt";
        try {
            API_KEY = Files.readString(Path.of(fileUrl));
        } catch (IOException e) {
            //throw global
            try {
                throw new NoSuchFileException(fileUrl);
            } catch (NoSuchFileException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static String createUrl(String country_code, String town) {
        var uri = UriComponentsBuilder.newInstance();
        uri
                .scheme("https")
                .host(HOST)
                .path("/data/2.5/weather");
        if (town != null) {
            uri.queryParam("q", town);
        }

        if (country_code != null) {
            uri.queryParam("q", town);
        }

        uri
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY)
                .build();

        return uri.toUriString();
    }


}

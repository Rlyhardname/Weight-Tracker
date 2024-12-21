package com.dimitrov_solutions.weight_tracker.weather.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class UrlFacade {
    public static final Logger logger = LoggerFactory.getLogger(UrlFacade.class);

    /**
     * Root weather api url
     */
    private static final String HOST = "api.openweathermap.org";

    private static String API_KEY;

    static {
        String fileUrl = "src/main/resources/api_key.txt";
        try {
            API_KEY = Files.readString(Path.of(fileUrl));
        } catch (IOException e) {
            logger.error("Api directory changed, fix ASAP");
        }
    }

    /**
     * Creates url for weather API from database country/town or defaults to BG/Sofia if the values
     * are missing for some reason
     */
    public static String createUrl(String country_code, String town) {
        var uri = UriComponentsBuilder.newInstance();
        uri
                .scheme("https")
                .host(HOST)
                .path("/data/2.5/weather");
        if (Objects.nonNull(town) && !town.isEmpty()) {
            uri.queryParam("q", town);
        }else{
            uri.queryParam("q", "Sofia");
        }

        if (Objects.nonNull(country_code) && !country_code.isEmpty()) {
            uri.queryParam("q", town);
        }else{
            uri.queryParam("q", "BG");
        }

        uri
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY)
                .build();

        return uri.toUriString();
    }
}
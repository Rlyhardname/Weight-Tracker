package com.dimitrov_solutions.weight_tracker.controllers;

import com.dimitrov_solutions.weight_tracker.services.MultiRateLimitService;
import com.dimitrov_solutions.weight_tracker.services.WeatherService;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1.0/weather")
@RestController
public class WeatherController {
    private final MultiRateLimitService multiRateLimitService;
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(MultiRateLimitService multiRateLimitService, WeatherService weatherService) {
        this.weatherService = weatherService;
        this.multiRateLimitService = multiRateLimitService;
    }

    @GetMapping("/")
    public ResponseEntity<?> fetchWeatherDetails() {
        // Get JWT and extract Payload(email)

        // Stub
        String email = "someMail@gmail.com";
        Bucket bucket = multiRateLimitService.resolveBucket(email);
        if (!bucket.tryConsume(1)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
        }


        return ResponseEntity.ok(weatherService.sendRequestReturnDetailsDto(email));
    }

}

package com.dimitrov_solutions.weight_tracker.models.weather;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

public class WeatherHttpClientFacade {
    private final HttpClient client;
    private final HttpRequest request;

    public WeatherHttpClientFacade(String url) {
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "text/html")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    public String send() {
        Callable<HttpResponse<String>> response = () -> client.send(request, HttpResponse.BodyHandlers.ofString());
        try {
            return response.call().body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

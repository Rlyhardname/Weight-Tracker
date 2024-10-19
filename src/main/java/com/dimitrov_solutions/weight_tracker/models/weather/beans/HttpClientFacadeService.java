package com.dimitrov_solutions.weight_tracker.models.weather.beans;

import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

@Service
public class HttpClientFacadeService {
    private final HttpClient client;

    public HttpClientFacadeService() {
        client = HttpClient.newHttpClient();
    }

    /**
     *
     * @param url
     * @return ResponseBody from API request.
     */
    public String send(String url) {
        Callable<HttpResponse<String>> response = () -> client.send(buildRequest(url), HttpResponse.BodyHandlers.ofString());
        try {
            return response.call().body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest buildRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("accept", "text/html")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }
}

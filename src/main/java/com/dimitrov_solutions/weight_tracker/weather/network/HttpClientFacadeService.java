package com.dimitrov_solutions.weight_tracker.weather.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HttpClientFacadeService {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientFacadeService.class);
    private final HttpClient client;

    public HttpClientFacadeService() {
        client = HttpClient.newHttpClient();
    }

    /**
     * @param url
     * @return ResponseBody from API request.
     */
    public String send(String url) {
        try {
            HttpResponse<String> response = client.send(buildRequest(url), HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.debug("Api failed response!");
                throw new RuntimeException();
            }

            return response.body();
        } catch (IOException | InterruptedException e) {
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

package org.example.integration;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Component
public class OpenF1ApiProvider implements F1EventProvider {
    private static final String BASE_URL = "https://api.openf1.org/v1";
    private final RestTemplate restTemplate;

    public OpenF1ApiProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<F1Event> getEvents(Integer year, String sessionType, String country, String sessionName) {
        StringBuilder url = new StringBuilder(BASE_URL + "/sessions");

        if (year != null || sessionType != null || country != null) {
            url.append("?");
        }

        if (country != null && !country.isEmpty()) {
            url.append("country_name=").append(country).append("&");
        }
        if (sessionType != null && !sessionType.isEmpty()) {
            url.append("session_type=").append(sessionType).append("&");
        }
        if (sessionName != null && !sessionName.isEmpty()) {
            url.append("session_name=").append(sessionName).append("&");
        }
        if (year != null) {
            url.append("year=").append(year).append("&");
        }

        ResponseEntity<List<F1Event>> response = restTemplate.exchange(
                url.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<F1Event>>() {}
        );

        return response.getBody();
    }

    @Override
    public List<F1Driver> getDriversForSession(Integer sessionKey) {
        String url = BASE_URL + "/drivers?session_key=" + sessionKey;

        ResponseEntity<List<F1Driver>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<F1Driver>>() {}
        );

        return response.getBody();
    }
}
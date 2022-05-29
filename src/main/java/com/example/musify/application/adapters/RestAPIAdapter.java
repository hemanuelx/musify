package com.example.musify.application.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class RestAPIAdapter {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    protected  <T> T getForObject(String apiUrl, Class<T> tClass) {
        return getForObject(apiUrl, tClass, "circuitbreaker");
    }

    protected  <T> T getForObject(String apiUrl, Class<T> tClass, String cb) {
        RestTemplate restTemplate = new RestTemplate();
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create(cb);

        return circuitBreaker.run(() -> restTemplate.getForObject(apiUrl, tClass), throwable -> getForObjectFallback(apiUrl));
    }

    private  <T> T getForObjectFallback(String apiUrl) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Request to API " + apiUrl + " was not possible");
    }
}

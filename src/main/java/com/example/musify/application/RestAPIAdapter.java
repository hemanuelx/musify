package com.example.musify.application;

import org.springframework.web.client.RestTemplate;

public class RestAPIAdapter {
    public <T> T getForObject(String apiUrl, Class<T> tClass) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, tClass);
    }
}

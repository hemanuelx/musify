package com.example.musify.application.adapters;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestAPIAdapter {
    protected  <T> T getForObject(String apiUrl, Class<T> tClass) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, tClass);
    }
}

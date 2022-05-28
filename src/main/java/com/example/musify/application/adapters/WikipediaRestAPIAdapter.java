package com.example.musify.application.adapters;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class WikipediaRestAPIAdapter extends RestAPIAdapter {
    private final static String API_URL = "https://en.wikipedia.org/api/rest_v1/page/summary/";

    public ObjectNode getArtistData(String name) {
        String apiURL = API_URL  + name;

        return this.getForObject(apiURL, ObjectNode.class);
    }
}

package com.example.musify.application.adapters;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

@Component
public class CoverArtArchiveRestAPIAdapter extends RestAPIAdapter {
    private final static String API_URL = "http://coverartarchive.org/release-group/";

    public ObjectNode getImageDate(String albumId) {
        String apiURL = API_URL + albumId;

        return this.getForObject(apiURL, ObjectNode.class);
    }
}

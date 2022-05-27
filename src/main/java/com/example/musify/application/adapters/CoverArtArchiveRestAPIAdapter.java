package com.example.musify.application.adapters;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class CoverArtArchiveRestAPIAdapter extends RestAPIAdapter {
    private final static String API_URL = "http://coverartarchive.org/release-group/";

    public ObjectNode getForObject(String id) {
        String apiURL = API_URL + id;

        return this.getForObject(apiURL, ObjectNode.class);
    }
}

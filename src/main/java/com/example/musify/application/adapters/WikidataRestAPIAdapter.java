package com.example.musify.application.adapters;

import com.example.musify.application.adapters.RestAPIAdapter;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WikidataRestAPIAdapter extends RestAPIAdapter {
    private final static String API_URL = "https://www.wikidata.org/wiki/Special:EntityData/";
    private final static String FORMAT = ".json";

    public ObjectNode getForObject(String id) {
        String apiURL = API_URL + id + FORMAT;

        return this.getForObject(apiURL, ObjectNode.class);
    }
}

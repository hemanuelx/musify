package com.example.musify.application.adapters;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

@Component
public class WikidataRestAPIAdapter extends RestAPIAdapter {
    private final static String API_URL = "https://www.wikidata.org/wiki/Special:EntityData/";
    private final static String FORMAT = ".json";

    public ObjectNode getArtistData(String id) {
        String apiURL = API_URL + id + FORMAT;

        return this.getForObject(apiURL, ObjectNode.class);
    }
}

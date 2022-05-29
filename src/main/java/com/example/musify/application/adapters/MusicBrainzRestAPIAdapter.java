package com.example.musify.application.adapters;

import com.example.musify.entities.musicbrainz.Artist;
import org.springframework.stereotype.Component;

@Component
public class MusicBrainzRestAPIAdapter extends RestAPIAdapter {
    private final static String API_URL = "http://musicbrainz.org/ws/2/artist/";
    private final static String API_URL_QUERY_PARAMETERS = "?fmt=json&inc=url-rels+release-groups";

    public Artist getArtist(String mbid) {
        String apiURL = API_URL + mbid + API_URL_QUERY_PARAMETERS;

        return this.getForObject(apiURL, Artist.class, "artistCB");
    }
}

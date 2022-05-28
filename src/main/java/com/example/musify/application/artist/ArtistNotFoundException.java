package com.example.musify.application.artist;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ArtistNotFoundException extends ResponseStatusException {
    public ArtistNotFoundException(HttpStatus status, String mbid) {
        super(status, "We couldn't find the artist with mbid = "+mbid);
    }
}

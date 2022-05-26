package com.example.musify.controllers;

import com.example.musify.application.MusicArtistService;
import com.example.musify.entities.ArtistDetailsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "")
public class MusicArtistController {

    private MusicArtistService musicArtistService = new MusicArtistService();

    @GetMapping(value = "musify/music-artist/details/{mbid}", produces = "application/json")
    public ArtistDetailsResponse findDetailsById(@PathVariable String mbid) {
        return musicArtistService.getArtistDetails(mbid);
    }

}

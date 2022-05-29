package com.example.musify.controllers;

import com.example.musify.application.MusicArtistService;
import com.example.musify.entities.ArtistDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicArtistController {
    final private MusicArtistService musicArtistService;

    public MusicArtistController(MusicArtistService musicArtistService) {
        this.musicArtistService = musicArtistService;
    }

    @GetMapping(value = "musify/music-artist/details/{mbid}", produces = "application/json")
    public ArtistDetails getMusicArtistDetails(@PathVariable String mbid) {
        System.out.println("START " +System.currentTimeMillis());
        ArtistDetails musicArtistDetails = musicArtistService.getMusicArtistDetails(mbid);
        System.out.println("END " +System.currentTimeMillis());

        return musicArtistDetails;
    }

}

package com.example.musify.controllers;

import com.example.musify.application.MusicArtistService;
import com.example.musify.application.AlbumComponent;
import com.example.musify.entities.ArtistDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "")
public class MusicArtistController {

    private AlbumComponent albumComponent;

    public MusicArtistController(AlbumComponent albumComponent) {
        this.albumComponent = albumComponent;
    }

    @GetMapping(value = "musify/music-artist/details/{mbid}", produces = "application/json")
    public ArtistDetails getMusicArtistDetails(@PathVariable String mbid) {
        MusicArtistService musicArtistService = new MusicArtistService(albumComponent);
        return musicArtistService.getMusicArtistDetails(mbid);
    }

}

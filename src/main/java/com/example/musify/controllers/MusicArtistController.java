package com.example.musify.controllers;

import com.example.musify.application.MusicArtistComponent;
import com.example.musify.application.AlbumService;
import com.example.musify.entities.ArtistDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "")
public class MusicArtistController {

    private AlbumService albumService;

    public MusicArtistController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "musify/music-artist/details/{mbid}", produces = "application/json")
    public ArtistDetails getMusicArtistDetails(@PathVariable String mbid) {
        MusicArtistComponent musicArtistComponent = new MusicArtistComponent(albumService);
        return musicArtistComponent.getMusicArtistDetails(mbid);
    }

}

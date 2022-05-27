package com.example.musify.application;

import com.example.musify.application.adapters.WikidataRestAPIAdapter;
import com.example.musify.application.adapters.WikipediaRestAPIAdapter;
import com.example.musify.entities.Album;
import com.example.musify.entities.ArtistDetails;
import com.example.musify.entities.musicbrainz.Artist;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class MusicArtistComponent {
    private final AlbumService albumService;

    public MusicArtistComponent(AlbumService albumService) {
        this.albumService = albumService;
    }

    public ArtistDetails getMusicArtistDetails(String mbid) {
        ArtistComponent artistComponent = new ArtistComponent();
        Artist artist =  artistComponent.getArtist(mbid);
        List<CompletableFuture<Album>> albumCompletableFutureList = artistComponent.createAlbumCompletableFutureList(albumService, artist);
        String wikipediaExtractHtml = artistComponent.getWikipediaExtractHtml(artistComponent, artist);

        List<Album> albums = artistComponent.getAlbumsFromCompletableFutureList(albumCompletableFutureList);
        return createArtistDetails(artist, wikipediaExtractHtml, albums);
    }


    private ArtistDetails createArtistDetails(Artist artist, String description, List<Album> albums) {
        ArtistDetails artistDetails = new ArtistDetails();
        artistDetails.setName(artist.getName());
        artistDetails.setCountry(artist.getCountry());
        artistDetails.setDescription(description);
        artistDetails.setDisambiguation(artist.getDisambiguation());
        artistDetails.setGender(artist.getGender());
        artistDetails.setMbid(artist.getId());
        artistDetails.setAlbums(albums);

        return artistDetails;
    }


    private String getUrlLastElement(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}

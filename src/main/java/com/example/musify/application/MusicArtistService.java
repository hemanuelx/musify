package com.example.musify.application;

import com.example.musify.application.album.AlbumComponent;
import com.example.musify.application.artist.ArtistComponent;
import com.example.musify.entities.Album;
import com.example.musify.entities.ArtistDetails;
import com.example.musify.entities.musicbrainz.Artist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MusicArtistService {
    private final AlbumComponent albumComponent;
    private final ArtistComponent artistComponent;

    public MusicArtistService(AlbumComponent albumComponent, ArtistComponent artistComponent) {
        this.albumComponent = albumComponent;
        this.artistComponent = artistComponent;
    }

    public ArtistDetails getMusicArtistDetails(String mbid) {
        Artist artist = artistComponent.getArtist(mbid);
        List<CompletableFuture<Album>> albumCompletableFutureList = artistComponent.createAlbumCompletableFutureList(albumComponent, artist);
        String wikipediaExtractHtml = artistComponent.getWikipediaExtractHtml(artist);

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
}

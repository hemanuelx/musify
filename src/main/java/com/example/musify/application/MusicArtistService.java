package com.example.musify.application;

import com.example.musify.application.musicbrainz.MusicBrainzRestAPIAdapter;
import com.example.musify.entities.ArtistDetailsResponse;
import com.example.musify.entities.musicbrainz.Artist;

public class MusicArtistService<T> {
    public ArtistDetailsResponse getArtistDetails(String mbid) {
        MusicBrainzRestAPIAdapter restAPIAdapter = new MusicBrainzRestAPIAdapter();
        Artist artist =  restAPIAdapter.getArtist(mbid);
        ArtistDetailsResponse artistDetailsResponse = new ArtistDetailsResponse();
        artistDetailsResponse.setName(artist.getName());
        artistDetailsResponse.setCountry(artist.getCountry());
//        artistDetailsResponse.setDescription();
//        artistDetailsResponse.setAlbums();
        artistDetailsResponse.setDisambiguation(artist.getDisambiguation());
        artistDetailsResponse.setGender(artist.getGender());
        artistDetailsResponse.setMbid(artist.getId());

        return artistDetailsResponse;
    }
}

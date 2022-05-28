package com.example.musify.application;

import com.example.musify.application.adapters.MusicBrainzRestAPIAdapter;
import com.example.musify.application.adapters.WikidataRestAPIAdapter;
import com.example.musify.application.adapters.WikipediaRestAPIAdapter;
import com.example.musify.application.artist.ArtistComponent;
import com.example.musify.entities.musicbrainz.Artist;
import com.example.musify.entities.musicbrainz.Relation;
import com.example.musify.entities.musicbrainz.Url;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtistComponentTest {
    @Mock
    MusicBrainzRestAPIAdapter musicBrainzRestAPIAdapter;

    @Mock
    WikidataRestAPIAdapter wikidataRestAPIAdapter;

    @Mock
    WikipediaRestAPIAdapter wikipediaRestAPIAdapter;

    ArtistComponent artistComponent;

    @BeforeEach
    public void setUp() {
        artistComponent = new ArtistComponent(musicBrainzRestAPIAdapter, wikidataRestAPIAdapter, wikipediaRestAPIAdapter);
    }

    @Test
    public void getWikidataId() {
        assertEquals("Q2831", artistComponent.getWikidataId(createArtist("some-mbid")));
    }

    @Test
    public void getArtist() {
        Mockito.when(musicBrainzRestAPIAdapter.getArtist("some-mbid")).thenReturn(createArtist("some-mbid"));
        assertEquals("some-mbid", artistComponent.getArtist("some-mbid").getId());
    }

    public Artist createArtist(String id) {
        Url url = new Url();
        url.setResource("https://www.wikidata.org/wiki/Q2831");

        Relation relation = new Relation();
        relation.setUrl(url);
        relation.setType("wikidata");

        Artist artist = new Artist();
        artist.setId(id);
        artist.setRelations(Arrays.asList(relation));

        return artist;
    }
}


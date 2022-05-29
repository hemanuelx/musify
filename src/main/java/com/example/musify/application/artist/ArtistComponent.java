package com.example.musify.application.artist;

import com.example.musify.application.adapters.MusicBrainzRestAPIAdapter;
import com.example.musify.application.adapters.WikidataRestAPIAdapter;
import com.example.musify.application.adapters.WikipediaRestAPIAdapter;
import com.example.musify.application.album.AlbumComponent;
import com.example.musify.entities.Album;
import com.example.musify.entities.musicbrainz.Artist;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class ArtistComponent {

    private final MusicBrainzRestAPIAdapter musicBrainzRestAPIAdapter;
    private final WikidataRestAPIAdapter wikidataRestAPIAdapter;
    private final WikipediaRestAPIAdapter wikipediaRestAPIAdapter;


    public ArtistComponent(MusicBrainzRestAPIAdapter musicBrainzRestAPIAdapter, WikidataRestAPIAdapter wikidataRestAPIAdapter,
                           WikipediaRestAPIAdapter wikipediaRestAPIAdapter) {
        this.musicBrainzRestAPIAdapter = musicBrainzRestAPIAdapter;
        this.wikidataRestAPIAdapter = wikidataRestAPIAdapter;
        this.wikipediaRestAPIAdapter = wikipediaRestAPIAdapter;
    }

    public Artist getArtist(String mbid) throws ArtistNotFoundException {
        try {
            return musicBrainzRestAPIAdapter.getArtist(mbid);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ArtistNotFoundException(e.getStatusCode(), mbid);
            }
            throw e;
        }
    }

    public List<CompletableFuture<Album>> createAlbumCompletableFutureList(AlbumComponent albumComponent, Artist artist) {
        List<CompletableFuture<Album>> albumCompletableFutureList = new ArrayList<>();
        if (artist.getAlbums() != null) {
            artist.getAlbums().forEach(album -> albumCompletableFutureList.add(albumComponent.getAlbumWithImageURL(album.getId(), album.getTitle())));
        }

        return albumCompletableFutureList;
    }

    public List<Album> getAlbumsFromCompletableFutureList(List<CompletableFuture<Album>> completableFutureList) {
        List<Album> albums = new ArrayList<>();
        albums.addAll(completableFutureList.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toCollection(ArrayList::new)));
        return albums;
    }

    public String getWikidataId(Artist artist) {
        return getUrlLastElement(artist.getRelations().stream().filter(relation -> relation.getType().equals("wikidata")).findFirst().get().getUrl().getResource());
    }

    @Nullable
    public String getWikipediaExtractHtml(Artist artist) {
        try {
            String artistWikidataId = getWikidataId(artist);
            ObjectNode wikidataResponse = wikidataRestAPIAdapter.getArtistData(artistWikidataId);
            String wikiUrl = getWikiUrl(artistWikidataId, wikidataResponse);
            ObjectNode wikipediaResponse = wikipediaRestAPIAdapter.getArtistData(getUrlLastElement(wikiUrl));
            return wikipediaResponse.get("extract_html").asText();
        } catch (Exception e) {
            System.out.println("Not able to lado Wikipedia data for artist "+artist.getId());
        }
        return null;
    }

    private String getWikiUrl(String artistWikidataId, ObjectNode wikidataResponse) {
        return wikidataResponse.get("entities").get(artistWikidataId).get("sitelinks").get("enwiki").get("url").asText();
    }

    private String getUrlLastElement(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}

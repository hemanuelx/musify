package com.example.musify.application;

import com.example.musify.application.adapters.MusicBrainzRestAPIAdapter;
import com.example.musify.application.adapters.WikidataRestAPIAdapter;
import com.example.musify.application.adapters.WikipediaRestAPIAdapter;
import com.example.musify.entities.Album;
import com.example.musify.entities.musicbrainz.Artist;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class ArtistComponent {

    public Artist getArtist(String mbid) {
        return new MusicBrainzRestAPIAdapter().getArtist(mbid);
    }

    public List<CompletableFuture<Album>> createAlbumCompletableFutureList(AlbumService albumService, Artist artist) {
        List<CompletableFuture<Album>> albumCompletableFutureList = new ArrayList<>();
        if (artist.getAlbums() != null) {
            artist.getAlbums().forEach(album -> albumCompletableFutureList.add(albumService.getAlbumWithImageURL(album.getId(), album.getTitle())));
        }

        return albumCompletableFutureList;
    }

    public List<Album> getAlbumsFromCompletableFutureList(List<CompletableFuture<Album>> completableFutureList) {
        List<Album> albums = new ArrayList<>();
        albums.addAll(completableFutureList.stream()
                .map(completableFuture -> completableFuture.join())
                .collect(Collectors.toCollection(ArrayList::new)));
        return albums;
    }

    public String getWikidataId(Artist artist) {
        return getUrlLastElement(artist.getRelations().stream().filter(relation -> relation.getType().equals("wikidata")).findFirst().get().getUrl().getResource());
    }

    public String getWikipediaExtractHtml(ArtistComponent artistComponent, Artist artist) {
        String artistWikidataId = artistComponent.getWikidataId(artist);
        ObjectNode wikidataResponse = new WikidataRestAPIAdapter().getForObject(artistWikidataId);
        String wikiUrl = getWikiUrl(artistWikidataId, wikidataResponse);
        ObjectNode wikipediaResponse = new WikipediaRestAPIAdapter().getForObject(getUrlLastElement(wikiUrl));
        return wikipediaResponse.get("extract_html").asText();
    }

    private String getWikiUrl(String artistWikidataId, ObjectNode wikidataResponse) {
        return wikidataResponse.get("entities").get(artistWikidataId).get("sitelinks").get("enwiki").get("url").asText();
    }

    private String getUrlLastElement(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}

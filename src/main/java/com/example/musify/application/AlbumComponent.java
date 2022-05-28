package com.example.musify.application;

import com.example.musify.application.adapters.CoverArtArchiveRestAPIAdapter;
import com.example.musify.entities.Album;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AlbumComponent {
    @Async
    public CompletableFuture<Album> getAlbumWithImageURL(String id, String title) {
        ObjectNode coverArtArchive = new CoverArtArchiveRestAPIAdapter().getImageDate(id);
        return CompletableFuture.completedFuture(
                new Album(id, title, coverArtArchive.get("images").get(0).get("image").asText())
        );
    }

}

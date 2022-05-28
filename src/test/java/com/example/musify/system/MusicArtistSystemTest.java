package com.example.musify.system;

import com.example.musify.controllers.MusicArtistController;
import com.example.musify.entities.ArtistDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MusicArtistSystemTest {
    MusicArtistController musicArtistController;

    @Autowired
    public MusicArtistSystemTest(MusicArtistController musicArtistController) {
        this.musicArtistController = musicArtistController;
    }

    @Test
    public void requestMickaelJackson() {
        ArtistDetails musicArtistDetails = musicArtistController.getMusicArtistDetails("f27ec8db-af05-4f36-916e-3d57f91ecf5e");
        Assertions.assertEquals("f27ec8db-af05-4f36-916e-3d57f91ecf5e", musicArtistDetails.getMbid());
        Assertions.assertEquals("Michael Jackson", musicArtistDetails.getName());
    }
}

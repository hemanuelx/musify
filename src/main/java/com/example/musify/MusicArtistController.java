package com.example.musify;

import com.example.musify.domain.Artist;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController(value = "")
public class MusicArtistController {
    @GetMapping(value = "musify/music-artist/details/{mbid}", produces = "application/json")
    public Artist findById(@PathVariable String mbid) {
        String api = "http://musicbrainz.org/ws/2/artist/"+mbid+"?fmt=json&inc=url-rels+release-groups";
        RestTemplate restTemplate = new RestTemplate();
        Artist result = restTemplate.getForObject(api, Artist.class);

        return result;
    }

}

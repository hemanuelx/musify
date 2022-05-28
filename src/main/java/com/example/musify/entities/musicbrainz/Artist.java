package com.example.musify.entities.musicbrainz;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Artist {
    private String name;

    private String id;

    private String gender;

    private String country;

    private String disambiguation;

    private List<Relation> relations;

    public List<Relation> getRelations() {
        return relations;
    }

    @JsonProperty("release-groups")
    private List<Album> albums;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public void setId(String id) {
        this.id = id;
    }
}

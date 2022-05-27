package com.example.musify.entities;

import java.util.List;

public class ArtistDetails {
    private String mbid;
    private String name;
    private String gender;
    private String country;
    private String disambiguation;
    private String description;
    private List<Album> albums;

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return name;
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

    public String getDescription() {
        return description;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}

package com.example.musify.entities.musicbrainz;

public class Relation {
    private String type;

    private Url url;

    public Url getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}

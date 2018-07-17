package com.tracks.app.service.dto;


import java.util.List;

public class TrackDTO {
    private String name;
    private List<String> artists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }
}

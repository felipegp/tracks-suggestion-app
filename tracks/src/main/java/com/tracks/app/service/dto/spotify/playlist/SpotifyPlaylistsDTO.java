
package com.tracks.app.service.dto.spotify.playlist;

import java.util.HashMap;
import java.util.Map;

public class SpotifyPlaylistsDTO {

    private Playlists playlists;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Playlists getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlists playlists) {
        this.playlists = playlists;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

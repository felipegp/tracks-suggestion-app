
package com.tracks.app.service.dto.spotify.track;

import java.util.HashMap;
import java.util.Map;

public class SpotifyResponseDTO {

    private Tracks tracks;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

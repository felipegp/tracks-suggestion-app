
package com.tracks.app.service.dto.spotify.playlist;

import java.util.HashMap;
import java.util.Map;

public class ExternalUrls {

    private String spotify;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

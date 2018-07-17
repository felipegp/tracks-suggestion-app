
package com.tracks.app.service.dto.spotify.playlist.track;

import java.util.HashMap;
import java.util.Map;

public class ExternalIds {

    private String isrc;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

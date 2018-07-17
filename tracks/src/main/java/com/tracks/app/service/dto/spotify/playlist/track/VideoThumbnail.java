
package com.tracks.app.service.dto.spotify.playlist.track;

import java.util.HashMap;
import java.util.Map;

public class VideoThumbnail {

    private Object url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

package com.tracks.app.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class HttpClientUtil {

    private final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    private RestTemplate restTemplate;

    public HttpClientUtil() {
        restTemplate = new RestTemplate();
    }

    public Object get(Map<String, String> urlParameters, Map<String, String> headersMap, String url, Class<?> responseClassType) throws UnsupportedEncodingException {
        HttpHeaders headers = buildHeader(headersMap);
        url = buildUrlWithParameters(urlParameters, url);
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseClassType);

        return response.getBody();
    }

    private String buildUrlWithParameters(Map<String, String> parameters, String url) throws UnsupportedEncodingException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);


        if (parameters != null) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                log.debug("Add query parameters: " + entry.getKey() + " : " + entry.getValue());
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        return java.net.URLDecoder.decode(builder.toUriString(), "UTF-8");
    }

    private HttpHeaders buildHeader(Map<String, String> headersMap) {
        HttpHeaders headers = new HttpHeaders();

        if (headersMap != null) {
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                log.debug("Add header: " + entry.getKey() + " : " + entry.getValue());
                headers.set(entry.getKey(), entry.getValue());
            }
        }

        return headers;
    }
}

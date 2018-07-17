package com.tracks.app.service;

import com.tracks.app.config.custom.OpenWeatherConfiguration;
import com.tracks.app.service.dto.LocationDTO;
import com.tracks.app.service.dto.weather.WeatherWrapper;
import com.tracks.app.service.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenWeatherService {

    private static final String LAT_STR = "lat";
    private static final String LONG_STR = "lon";
    private static final String Q_STR = "q";
    private static final String UNITS_STR = "units";
    private static final String APPID_STR = "APPID";

    @Autowired
    private OpenWeatherConfiguration openWeatherConfiguration;

    public WeatherWrapper getLocationWeather(LocationDTO location) throws UnsupportedEncodingException {
        Map<String, String> urlParameters = buildUrlParameters(location);
        String url = openWeatherConfiguration.getBaseUrl();

        return (WeatherWrapper) new HttpClientUtil().get(urlParameters, null, url, WeatherWrapper.class);
    }

    private Map<String, String> buildUrlParameters(LocationDTO location) {
        Map<String, String> parameters = new HashMap<>();

        if (location.getCoordinates() != null) {
            parameters.put(LAT_STR, Long.toString(location.getCoordinates().getLagitude()));
            parameters.put(LONG_STR, Long.toString(location.getCoordinates().getLongitude()));

        } else {
            parameters.put(Q_STR, location.getCity());
        }

        parameters.put(UNITS_STR, openWeatherConfiguration.getUnits());
        parameters.put(APPID_STR, openWeatherConfiguration.getApiKey());

        return parameters;
    }
}

package com.tracks.app.service;

import com.tracks.app.service.dto.LocationDTO;
import com.tracks.app.service.dto.TrackDTO;
import com.tracks.app.service.dto.spotify.playlist.track.Artist_;
import com.tracks.app.service.dto.spotify.playlist.track.Item;
import com.tracks.app.service.dto.weather.WeatherWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TracksService {

    @Autowired
    private OpenWeatherService openWeatherService;

    @Autowired
    private SpotifyService spotifyService;

    private static final String PARTY_CATEGORY = "party";
    private static final String POP_CATEGORY = "pop";
    private static final String ROCK_CATEGORY = "rock";
    private static final String CLASSICAL_CATEGORY = "classical";

    private static final Double PARTY_TEMP_MIN = 30d;
    private static final Double POP_TEMP_MAX = 30d;
    private static final Double POP_TEMP_MIN = 15d;
    private static final Double ROCK_TEMP_MAX = 14d;
    private static final Double ROCK_TEMP_MIN = 10d;

    public List<TrackDTO> getTracks(LocationDTO locationInput) throws UnsupportedEncodingException {
        List<TrackDTO> tracks = new ArrayList<>();
        List<Item> spotifyTracks = getSpotifyTracks(locationInput);

        for (Item item: spotifyTracks) {

            if (item.getTrack() != null) {
                TrackDTO track = new TrackDTO();
                List<Artist_> artists = item.getTrack().getArtists();
                List<String> artistsStr = new ArrayList<>();

                for (Artist_ artist_ : artists) {
                    artistsStr.add(artist_.getName());
                }

                track.setArtists(artistsStr);
                track.setName(item.getTrack().getName());
                tracks.add(track);
            }
        }

        return tracks;
    }

    private List<Item> getSpotifyTracks(LocationDTO locationInput) throws UnsupportedEncodingException {
        WeatherWrapper weather = openWeatherService.getLocationWeather(locationInput);
        String trackCategory;
        List<Item> tracks = null;

        if (weather != null && weather.getMain() != null) {
            trackCategory = getTrackGender(weather.getMain().getTemp());
            tracks = spotifyService.getTracks(trackCategory);
        }

        return tracks;
    }

    private String getTrackGender(Double temperature) {
        String tracksCategory;

        if (Double.compare(temperature, PARTY_TEMP_MIN) > 0) {
            tracksCategory = PARTY_CATEGORY;

        } else if (
                Double.compare(temperature, POP_TEMP_MAX) == 0 ||
                Double.compare(temperature, POP_TEMP_MIN) > 0 ||
                Double.compare(temperature, POP_TEMP_MIN) == 0
        ) {
            tracksCategory = POP_CATEGORY;

        } else if (
                Double.compare(temperature, ROCK_TEMP_MAX) == 0 ||
                Double.compare(temperature, ROCK_TEMP_MIN) > 0 ||
                Double.compare(temperature, ROCK_TEMP_MIN) == 0
        ) {
            tracksCategory = ROCK_CATEGORY;

        } else {
            tracksCategory = CLASSICAL_CATEGORY;
        }

        return tracksCategory;
    }

}

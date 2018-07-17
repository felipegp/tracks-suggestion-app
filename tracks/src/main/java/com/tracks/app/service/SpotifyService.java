package com.tracks.app.service;

import com.tracks.app.config.custom.SpotifyConfiguration;
import com.tracks.app.service.dto.spotify.playlist.Item;
import com.tracks.app.service.dto.spotify.playlist.Playlists;
import com.tracks.app.service.dto.spotify.playlist.SpotifyPlaylistsDTO;
import com.tracks.app.service.dto.spotify.playlist.track.SpotifyPlaylistsTracksDTO;
import com.tracks.app.service.util.HttpClientUtil;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpotifyService {

    @Autowired
    private SpotifyConfiguration spotifyConfiguration;

    private String SPOTIFY_ACCESS_TOKEN;

    public List<com.tracks.app.service.dto.spotify.playlist.track.Item> getTracks(String category) throws UnsupportedEncodingException {
        Playlists playlists;
        List<com.tracks.app.service.dto.spotify.playlist.track.Item> tracks = null;

        getSpotifyToken();
        SpotifyPlaylistsDTO spotifyPlaylistsDTO = getPlaylists(category);

        if (spotifyPlaylistsDTO != null) {
            playlists = spotifyPlaylistsDTO.getPlaylists();

            if (playlists.getItems() != null && !playlists.getItems().isEmpty()) {
                SpotifyPlaylistsTracksDTO spotifyPlaylistsTracksDTO = getPlaylistTrack(playlists.getItems().get(0));
                tracks = spotifyPlaylistsTracksDTO.getItems();
            }
        }

        return tracks;
    }

    private void getSpotifyToken() {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(spotifyConfiguration.getClientId())
            .setClientSecret(spotifyConfiguration.getClientSecret())
            .build();

        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

        try {
            ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            SPOTIFY_ACCESS_TOKEN = clientCredentials.getAccessToken();

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private SpotifyPlaylistsDTO getPlaylists(String category) throws UnsupportedEncodingException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + SPOTIFY_ACCESS_TOKEN);

        String url = spotifyConfiguration.getBaseUrl() + "browse/categories/" + category + "/playlists";

        return (SpotifyPlaylistsDTO) new HttpClientUtil().get(null, headers, url, SpotifyPlaylistsDTO.class);
    }

    private SpotifyPlaylistsTracksDTO getPlaylistTrack(Item playlist) throws UnsupportedEncodingException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + SPOTIFY_ACCESS_TOKEN);

        String url = spotifyConfiguration.getBaseUrl() + "users/" + playlist.getOwner().getId() + "/playlists/" + playlist.getId() + "/tracks";

        return (SpotifyPlaylistsTracksDTO) new HttpClientUtil().get(null, headers, url, SpotifyPlaylistsTracksDTO.class);
    }

}

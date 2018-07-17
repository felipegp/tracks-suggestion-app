package com.tracks.app.web.rest;

import com.tracks.app.service.TracksService;
import com.tracks.app.service.dto.CoordinatesDTO;
import com.tracks.app.service.dto.LocationDTO;
import com.tracks.app.service.dto.TrackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TracksResource {

    @Autowired
    private TracksService tracksService;

    @GetMapping("/tracks")
    public ResponseEntity<List<TrackDTO>> getTracks(
        @RequestParam(value = "city", required = false) String city,
        @RequestParam(value = "latitude", required = false) Long latitude,
        @RequestParam(value = "longitude", required = false) Long longitude
    ) throws UnsupportedEncodingException {

        LocationDTO location = new LocationDTO();

        if (latitude != null && longitude != null) {
            CoordinatesDTO coordinates = new CoordinatesDTO();
            coordinates.setLagitude(latitude);
            coordinates.setLongitude(longitude);
            location.setCoordinates(coordinates);

        } else if (city != null && !city.isEmpty()) {
            location.setCity(city);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok().body(tracksService.getTracks(location));
    }

}

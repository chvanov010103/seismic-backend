package com.example.seismic.service;

import com.example.seismic.model.EarthquakeEvent;
import com.example.seismic.repository.EarthquakeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class EarthquakeImporter {
    private final EarthquakeRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String USGS_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";

    public EarthquakeImporter(EarthquakeRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 300000)
    public void fetchAndSaveEarthquakes() {
        String response = restTemplate.getForObject(USGS_URL, String.class);
        JSONObject root = new JSONObject(response);
        JSONArray features = root.getJSONArray("features");

        for (int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);
            JSONObject properties = feature.getJSONObject("properties");
            JSONObject geometry = feature.getJSONObject("geometry");

            String location = properties.getString("place");
            double magnitude = properties.optDouble("mag", 0.0);
            double depth = geometry.getJSONArray("coordinates").getDouble(2);
            double longitude = geometry.getJSONArray("coordinates").getDouble(0);
            double latitude = geometry.getJSONArray("coordinates").getDouble(1);
            long timeMillis = properties.getLong("time");

            LocalDateTime timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneOffset.UTC);

            EarthquakeEvent event = new EarthquakeEvent();
            event.setLocation(location);
            event.setMagnitude(magnitude);
            event.setDepth(depth);
            event.setLatitude(latitude);
            event.setLongitude(longitude);
            event.setTimestamp(timestamp);

            if (!repository.existsByLocationAndTimestamp(location, timestamp)) {
                repository.save(event);
            }
        }
    }
}

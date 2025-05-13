package com.example.seismic.controller;

import com.example.seismic.model.EarthquakeEvent;
import com.example.seismic.service.EarthquakeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/earthquakes")
public class EarthquakeController {
    private final EarthquakeService service;

    public EarthquakeController(EarthquakeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EarthquakeEvent> getAllEvents() {
        return service.getAllEvents();
    }
}

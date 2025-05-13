package com.example.seismic.service;

import com.example.seismic.model.EarthquakeEvent;
import com.example.seismic.repository.EarthquakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EarthquakeService {
    private final EarthquakeRepository repository;

    public EarthquakeService(EarthquakeRepository repository) {
        this.repository = repository;
    }

    public List<EarthquakeEvent> getAllEvents() {
        return repository.findAll();
    }
}

package com.example.seismic.repository;

import com.example.seismic.model.EarthquakeEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EarthquakeRepository extends JpaRepository<EarthquakeEvent, Long> {
    boolean existsByLocationAndTimestamp(String location, LocalDateTime timestamp);
}

package com.example.seismic.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EarthquakeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private double magnitude;
    private double depth;
    private LocalDateTime timestamp;
    private double latitude;
    private double longitude;

    // Getters and setters
}

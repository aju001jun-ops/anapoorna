package com.anapoornabackend.demo.service;

import java.util.List;
import java.util.Optional;

import com.anapoornabackend.demo.entity.LocationPin;

public interface LocationService {

    // Save a new location
    LocationPin saveLocation(LocationPin location);

    // Get all locations
    List<LocationPin> getAllLocations();

    // Get location by ID
    Optional<LocationPin> getLocationById(Long id);

    // Update an existing location
    LocationPin updateLocation(LocationPin location);

    // Delete location
    void deleteLocation(Long id);
}
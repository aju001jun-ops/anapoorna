package com.anapoornabackend.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anapoornabackend.demo.entity.LocationPin;
import com.anapoornabackend.demo.service.LocationService;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/save")
    public LocationPin saveLocation(@RequestBody LocationPin location) {
        return locationService.saveLocation(location);
    }

    @GetMapping
    public List<LocationPin> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{id}")
    public LocationPin getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }
}
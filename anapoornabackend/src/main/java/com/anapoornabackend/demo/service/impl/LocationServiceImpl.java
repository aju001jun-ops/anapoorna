package com.anapoornabackend.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anapoornabackend.demo.entity.LocationPin;
import com.anapoornabackend.demo.repository.LocationRepository;
import com.anapoornabackend.demo.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public LocationPin saveLocation(LocationPin location) {
        return locationRepository.save(location);
    }

    @Override
    public List<LocationPin> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<LocationPin> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public LocationPin updateLocation(LocationPin location) {
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

}
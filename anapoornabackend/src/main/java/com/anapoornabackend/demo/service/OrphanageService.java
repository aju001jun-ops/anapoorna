package com.anapoornabackend.demo.service;

import java.util.List;
import java.util.Optional;

import com.anapoornabackend.demo.entity.Orphanage;

public interface OrphanageService {

    // Save a new orphanage
    Orphanage saveOrphanage(Orphanage orphanage);

    // Get all orphanages
    List<Orphanage> getAllOrphanages();

    // Get orphanage by ID
    Optional<Orphanage> getOrphanageById(Long id);

    // Update orphanage
    Orphanage updateOrphanage(Orphanage orphanage);

    // Delete orphanage
    void deleteOrphanage(Long id);
}
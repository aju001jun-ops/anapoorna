 package com.anapoornabackend.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anapoornabackend.demo.entity.Donation;
import com.anapoornabackend.demo.repository.DonationRepository;

@Service
public class DonationService {

    @Autowired
    private DonationRepository repository;

    // Save Donation
    public void save(Donation donation) {
        repository.save(donation);
    }

    // Get All Donations
    public List<Donation> getAll() {
        return repository.findAll();
    }

    // Search Donation by Donor Name
    public List<Donation> searchByDonorName(String keyword) {
        return repository.findByDonorNameContainingIgnoreCase(keyword);
    }

    // Get Donation by ID
    public Donation getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Delete Donation
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
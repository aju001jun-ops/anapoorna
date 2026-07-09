package com.anapoornabackend.demo.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anapoornabackend.demo.entity.Donation;
import com.anapoornabackend.demo.service.impl.DonationService;

@RestController
@RequestMapping("/api/donation")
public class DonationRestController {

    @Autowired
    private DonationService service;

    // Get All Donations
    @GetMapping("/list")
    public List<Donation> getAllDonations() {
        return service.getAll();
    }

    // Get Donation By ID
    @GetMapping("/{id}")
    public Donation getDonation(@PathVariable Long id) {
        return service.getById(id);
    }

}
package com.anapoornabackend.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.anapoornabackend.demo.entity.Donation;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anapoornabackend.demo.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    List<Donation> findByDonorNameContainingIgnoreCase(String donorName);

}

}
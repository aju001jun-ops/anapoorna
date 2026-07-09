package com.anapoornabackend.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anapoornabackend.demo.entity.Orphanage;

@Repository
public interface OrphanageRepository extends JpaRepository<Orphanage, Long> {

}
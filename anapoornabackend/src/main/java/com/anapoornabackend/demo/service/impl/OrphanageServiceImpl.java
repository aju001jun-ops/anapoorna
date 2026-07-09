package com.anapoornabackend.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anapoornabackend.demo.entity.Orphanage;
import com.anapoornabackend.demo.repository.OrphanageRepository;
import com.anapoornabackend.demo.service.OrphanageService;

@Service
public class OrphanageServiceImpl implements OrphanageService {

    @Autowired
    private OrphanageRepository orphanageRepository;

    @Override
    public Orphanage saveOrphanage(Orphanage orphanage) {
        return orphanageRepository.save(orphanage);
    }

    @Override
    public List<Orphanage> getAllOrphanages() {
        return orphanageRepository.findAll();
    }

    @Override
    public Optional<Orphanage> getOrphanageById(Long id) {
        return orphanageRepository.findById(id);
    }

    @Override
    public Orphanage updateOrphanage(Orphanage orphanage) {
        return orphanageRepository.save(orphanage);
    }

    @Override
    public void deleteOrphanage(Long id) {
        orphanageRepository.deleteById(id);
    }

}
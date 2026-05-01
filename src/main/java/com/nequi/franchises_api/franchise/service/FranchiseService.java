package com.nequi.franchises_api.franchise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nequi.franchises_api.franchise.entity.Franchise;
import com.nequi.franchises_api.franchise.repository.FranchiseRepository;

@Service
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseService(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    public Franchise create(String name) {
        Franchise franchise = new Franchise(name);
        return franchiseRepository.save(franchise);
    }

    public List<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    public Franchise findById(Long id) {
        return franchiseRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Franchise not found with id: " + id)
        );
    }

    public Franchise update(Long id, String name) {
        Franchise franchise = findById(id);
        franchise.setName(name);
        return franchiseRepository.save(franchise);
    }

    public void delete(Long id) {
        Franchise franchise = findById(id);
        franchiseRepository.delete(franchise);
    }
}

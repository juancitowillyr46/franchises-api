package com.nequi.franchises_api.franchise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nequi.franchises_api.franchise.entity.Franchise;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
        
}

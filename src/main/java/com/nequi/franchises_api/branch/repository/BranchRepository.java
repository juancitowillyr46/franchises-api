package com.nequi.franchises_api.branch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nequi.franchises_api.branch.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    
    @EntityGraph(attributePaths = {"products"})
    List<Branch> findByFranchiseId(Long franchiseId);

}

package com.nequi.franchises_api.franchise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nequi.franchises_api.franchise.dto.FranchiseResponse;
import com.nequi.franchises_api.franchise.entity.Franchise;

public interface FranchiseRepository extends JpaRepository<Franchise, Long> {

    @Query("""
            select new com.nequi.franchises_api.franchise.dto.FranchiseResponse(
                f.id,
                f.name
            )
            from Franchise f
            """)
    Page<FranchiseResponse> findAllSummaries(Pageable pageable);
}

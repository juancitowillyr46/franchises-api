package com.nequi.franchises_api.branch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nequi.franchises_api.branch.dto.BranchResponse;
import com.nequi.franchises_api.branch.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query("""
            select new com.nequi.franchises_api.branch.dto.BranchResponse(
                b.id,
                b.name,
                b.franchise.id
            )
            from Branch b
            """)
    Page<BranchResponse> findAllSummaries(Pageable pageable);

}

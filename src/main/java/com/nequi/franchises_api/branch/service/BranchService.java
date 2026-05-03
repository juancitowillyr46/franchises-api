package com.nequi.franchises_api.branch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nequi.franchises_api.branch.dto.BranchCreateRequest;
import com.nequi.franchises_api.branch.dto.BranchResponse;
import com.nequi.franchises_api.branch.dto.BranchUpdateRequest;

public interface BranchService {

    BranchResponse create(Long franchiseId, BranchCreateRequest request);

    Page<BranchResponse> findAll(Pageable pageable);

    BranchResponse findById(Long id);

    BranchResponse update(Long id, BranchUpdateRequest request);
}

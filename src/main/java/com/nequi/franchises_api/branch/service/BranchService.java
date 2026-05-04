package com.nequi.franchises_api.branch.service;

import java.util.List;

import com.nequi.franchises_api.branch.dto.BranchCreateRequest;
import com.nequi.franchises_api.branch.dto.BranchResponse;
import com.nequi.franchises_api.branch.dto.BranchUpdateRequest;

public interface BranchService {

    BranchResponse create(Long franchiseId, BranchCreateRequest request);

    List<BranchResponse> findAll();

    BranchResponse findById(Long id);

    BranchResponse update(Long id, BranchUpdateRequest request);
}

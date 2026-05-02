package com.nequi.franchises_api.branch.service;

import java.util.List;

import com.nequi.franchises_api.branch.dto.BranchRequest;
import com.nequi.franchises_api.branch.dto.BranchResponse;

public interface BranchService {

    BranchResponse create(BranchRequest request);

    List<BranchResponse> findAll();

    BranchResponse findById(Long id);

    BranchResponse update(Long id, BranchRequest request);

    void delete(Long id);
}

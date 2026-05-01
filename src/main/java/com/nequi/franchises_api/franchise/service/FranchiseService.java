package com.nequi.franchises_api.franchise.service;

import java.util.List;

import com.nequi.franchises_api.franchise.dto.FranchiseRequest;
import com.nequi.franchises_api.franchise.dto.FranchiseResponse;

public interface FranchiseService {
    
    List<FranchiseResponse> findAll();

    FranchiseResponse findById(Long id);
    
    FranchiseResponse create(FranchiseRequest request);
    
    FranchiseResponse update(Long id, FranchiseRequest request);
    
    void delete(Long id);
}

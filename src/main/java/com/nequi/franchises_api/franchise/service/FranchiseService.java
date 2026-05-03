package com.nequi.franchises_api.franchise.service;

import java.util.List;

import com.nequi.franchises_api.franchise.dto.FranchiseRequest;
import com.nequi.franchises_api.franchise.dto.FranchiseResponse;
import com.nequi.franchises_api.franchise.dto.TopStockProductResponse;

public interface FranchiseService {
    
    List<FranchiseResponse> findAll();

    FranchiseResponse findById(Long id);
    
    FranchiseResponse create(FranchiseRequest request);
    
    FranchiseResponse update(Long id, FranchiseRequest request);
    
    void delete(Long id);

    List<TopStockProductResponse> getTopStockProducts(Long franchiseId);
}

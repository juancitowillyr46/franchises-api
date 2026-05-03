package com.nequi.franchises_api.franchise.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nequi.franchises_api.franchise.dto.FranchiseRequest;
import com.nequi.franchises_api.franchise.dto.FranchiseResponse;
import com.nequi.franchises_api.franchise.dto.TopStockProductResponse;

public interface FranchiseService {

    Page<FranchiseResponse> findAll(Pageable pageable);

    FranchiseResponse findById(Long id);

    FranchiseResponse create(FranchiseRequest request);

    FranchiseResponse update(Long id, FranchiseRequest request);

    List<TopStockProductResponse> getTopStockProducts(Long franchiseId);
}

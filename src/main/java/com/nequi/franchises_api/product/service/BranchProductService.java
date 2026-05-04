package com.nequi.franchises_api.product.service;

import com.nequi.franchises_api.product.dto.BranchProductCreateRequest;
import com.nequi.franchises_api.product.dto.BranchProductResponse;
import com.nequi.franchises_api.product.dto.BranchProductStockUpdateRequest;

public interface BranchProductService {

    BranchProductResponse create(Long branchId, BranchProductCreateRequest request);

    BranchProductResponse updateStock(Long branchId, Long productId, BranchProductStockUpdateRequest request);

    void delete(Long branchId, Long productId);
}

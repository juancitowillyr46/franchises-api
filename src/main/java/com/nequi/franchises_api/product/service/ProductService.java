package com.nequi.franchises_api.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nequi.franchises_api.product.dto.ProductCreateRequest;
import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.dto.ProductStockUpdateRequest;
import com.nequi.franchises_api.product.dto.ProductUpdateRequest;

public interface ProductService {

    ProductResponse create(Long branchId, ProductCreateRequest request);

    Page<ProductResponse> findAll(Pageable pageable);

    ProductResponse findById(Long id);

    ProductResponse update(Long id, ProductUpdateRequest request);

    ProductResponse updateStock(Long id, ProductStockUpdateRequest request);

    void delete(Long id);
}

package com.nequi.franchises_api.product.service;

import java.util.List;

import com.nequi.franchises_api.product.dto.ProductCreateRequest;
import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.dto.ProductStockUpdateRequest;
import com.nequi.franchises_api.product.dto.ProductUpdateRequest;

public interface ProductService {

    ProductResponse create(ProductCreateRequest request);

    List<ProductResponse> findAll();

    ProductResponse findById(Long id);

    ProductResponse update(Long id, ProductUpdateRequest request);

    ProductResponse updateStock(Long id, ProductStockUpdateRequest request);

    void delete(Long id);
}

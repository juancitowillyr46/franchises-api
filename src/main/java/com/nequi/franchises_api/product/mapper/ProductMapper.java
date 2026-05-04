package com.nequi.franchises_api.product.mapper;

import org.springframework.stereotype.Component;

import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.entity.Product;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName()
        );
    }
}

package com.nequi.franchises_api.product.mapper;

import org.springframework.stereotype.Component;

import com.nequi.franchises_api.product.dto.BranchProductResponse;
import com.nequi.franchises_api.product.entity.BranchProduct;

@Component
public class BranchProductMapper {

    public BranchProductResponse toResponse(BranchProduct branchProduct) {
        return new BranchProductResponse(
                branchProduct.getId(),
                branchProduct.getBranch().getId(),
                branchProduct.getProduct().getId(),
                branchProduct.getProduct().getName(),
                branchProduct.getStock()
        );
    }
}

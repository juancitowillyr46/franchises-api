package com.nequi.franchises_api.franchise.dto;

public record TopStockProductResponse(
        Long productId,
        String productName,
        Integer stock,
        Long branchId,
        String branchName
) {
}
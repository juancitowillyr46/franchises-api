package com.nequi.franchises_api.franchise.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TopStockProductResponse(
        @Schema(example = "1") Long productId,
        @Schema(example = "Product 1") String productName,
        @Schema(example = "25") Integer stock,
        @Schema(example = "1") Long branchId,
        @Schema(example = "Branch 1") String branchName
) {
}

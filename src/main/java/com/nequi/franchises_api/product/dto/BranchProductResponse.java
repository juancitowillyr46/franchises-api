package com.nequi.franchises_api.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record BranchProductResponse(
        @Schema(example = "1") Long id,
        @Schema(example = "1") Long branchId,
        @Schema(example = "1") Long productId,
        @Schema(example = "Jabon A") String productName,
        @Schema(example = "10") Integer stock
) {
}

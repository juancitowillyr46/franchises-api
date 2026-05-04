package com.nequi.franchises_api.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BranchProductCreateRequest {

    @Schema(description = "Product identifier", example = "1")
    @NotNull(message = "Product ID is required")
    private Long productId;

    @Schema(description = "Initial stock for the branch", example = "10")
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    private Integer stock;

    public BranchProductCreateRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

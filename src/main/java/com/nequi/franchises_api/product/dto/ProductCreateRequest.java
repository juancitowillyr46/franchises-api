package com.nequi.franchises_api.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductCreateRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be a non-negative integer")
    private Integer stock;

    @NotNull(message = "Branch ID is required")
    private Long branchId;

    public ProductCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
}

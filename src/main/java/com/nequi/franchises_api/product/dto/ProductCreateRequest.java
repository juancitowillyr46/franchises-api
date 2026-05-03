package com.nequi.franchises_api.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class ProductCreateRequest {

    @Schema(description = "Product name", example = "Product 1")
    @NotBlank(message = "Product name is required")
    private String name;

    @Schema(description = "Initial stock", example = "10")
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    private Integer stock;

    public ProductCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

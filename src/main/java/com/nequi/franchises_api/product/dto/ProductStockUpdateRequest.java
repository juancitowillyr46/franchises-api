package com.nequi.franchises_api.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductStockUpdateRequest {

    @NotNull(message = "Product ID cannot be null")
    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    private Integer stock;

    public ProductStockUpdateRequest() {
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
}

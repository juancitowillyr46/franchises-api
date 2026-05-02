package com.nequi.franchises_api.product.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductUpdateRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    public ProductUpdateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

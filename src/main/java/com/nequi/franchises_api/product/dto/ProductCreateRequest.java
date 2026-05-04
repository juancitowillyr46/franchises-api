package com.nequi.franchises_api.product.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class ProductCreateRequest {

    @Schema(description = "Product name", example = "Product 1")
    @NotBlank(message = "Product name is required")
    private String name;

    public ProductCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

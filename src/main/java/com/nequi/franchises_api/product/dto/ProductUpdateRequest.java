package com.nequi.franchises_api.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class ProductUpdateRequest {

    @Schema(description = "Product name", example = "Product updated")
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

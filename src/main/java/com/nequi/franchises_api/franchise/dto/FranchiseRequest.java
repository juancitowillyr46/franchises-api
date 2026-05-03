package com.nequi.franchises_api.franchise.dto;

import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;

public class FranchiseRequest {

    @Schema(description = "Franchise name", example = "Franchise 1")
    @NotBlank(message = "Franchise name is required")
    private String name;

    public FranchiseRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

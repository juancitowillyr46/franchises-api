package com.nequi.franchises_api.branch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class BranchCreateRequest {
    
    @Schema(description = "Branch name", example = "Branch 1")
    @NotBlank(message = "Branch name is required")
    private String name;

    public BranchCreateRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

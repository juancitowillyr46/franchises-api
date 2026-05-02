package com.nequi.franchises_api.branch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BranchRequest {

    @NotBlank(message = "Branch name is required")
    private String name;

    @NotNull(message = "Franchise ID is required")
    private Long franchiseId;

    public String getName() {
        return name;
    }

    public Long getFranchiseId() {
        return franchiseId;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setFranchiseId(Long franchiseId) {
        this.franchiseId = franchiseId;
    }
}

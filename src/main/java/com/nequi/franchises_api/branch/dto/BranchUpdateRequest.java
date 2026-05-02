package com.nequi.franchises_api.branch.dto;

import jakarta.validation.constraints.NotBlank;

public class BranchUpdateRequest {

    @NotBlank(message = "Branch name is required")
    private String name;

    public BranchUpdateRequest() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

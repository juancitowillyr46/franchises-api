package com.nequi.franchises_api.franchise.dto;

import jakarta.validation.constraints.NotBlank;

public class FranchiseRequest {

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

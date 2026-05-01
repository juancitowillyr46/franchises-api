package com.nequi.franchises_api.franchise.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FranchiseRequest {

    @NotBlank(message = "Name is required")
    private String name;

    public FranchiseRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

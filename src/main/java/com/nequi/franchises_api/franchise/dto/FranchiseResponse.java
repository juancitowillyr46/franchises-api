package com.nequi.franchises_api.franchise.dto;

import lombok.Data;

@Data
public class FranchiseResponse {
    private Long id;
    private String name;
    
    public FranchiseResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

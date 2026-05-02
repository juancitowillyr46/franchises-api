package com.nequi.franchises_api.branch.dto;

public record BranchResponse(
    Long id,
    String name,
    Long franchiseId
) {
}

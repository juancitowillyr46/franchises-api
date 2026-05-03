package com.nequi.franchises_api.branch.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record BranchResponse(
    @Schema(example = "1") Long id,
    @Schema(example = "Branch 1") String name,
    @Schema(example = "1") Long franchiseId
) {
}

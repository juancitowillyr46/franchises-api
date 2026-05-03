package com.nequi.franchises_api.franchise.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record FranchiseResponse(
    @Schema(example = "1") Long id,
    @Schema(example = "Franchise 1") String name
) {
}

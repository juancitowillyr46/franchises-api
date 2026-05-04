package com.nequi.franchises_api.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProductResponse(
    @Schema(example = "1") Long id,
    @Schema(example = "Product 1") String name
) {

}

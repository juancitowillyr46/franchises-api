package com.nequi.franchises_api.product.dto;

public record ProductResponse(
    Long id,
    String name,
    Integer stock,
    Long branchId
) {

}

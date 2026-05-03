package com.nequi.franchises_api.franchise.mapper;

import org.springframework.stereotype.Component;

import com.nequi.franchises_api.franchise.dto.FranchiseResponse;
import com.nequi.franchises_api.franchise.entity.Franchise;

@Component
public class FranchiseMapper {

    public FranchiseResponse toResponse(Franchise franchise) {
        return new FranchiseResponse(
                franchise.getId(),
                franchise.getName()
        );
    }
}

package com.nequi.franchises_api.branch.mapper;

import org.springframework.stereotype.Component;

import com.nequi.franchises_api.branch.dto.BranchResponse;
import com.nequi.franchises_api.branch.entity.Branch;

@Component
public class BranchMapper {

    public BranchResponse toResponse(Branch branch) {
        return new BranchResponse(
                branch.getId(),
                branch.getName(),
                branch.getFranchise().getId()
        );
    }
}

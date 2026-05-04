package com.nequi.franchises_api.branch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nequi.franchises_api.branch.dto.BranchCreateRequest;
import com.nequi.franchises_api.branch.dto.BranchResponse;
import com.nequi.franchises_api.branch.dto.BranchUpdateRequest;
import com.nequi.franchises_api.branch.entity.Branch;
import com.nequi.franchises_api.branch.mapper.BranchMapper;
import com.nequi.franchises_api.branch.repository.BranchRepository;
import com.nequi.franchises_api.franchise.entity.Franchise;
import com.nequi.franchises_api.franchise.repository.FranchiseRepository;
import com.nequi.franchises_api.shared.exception.ResourceNotFoundException;

@Service
public class BranchServiceImpl implements BranchService {
    
    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;
    private final BranchMapper branchMapper;

    public BranchServiceImpl(
            BranchRepository branchRepository, 
            FranchiseRepository franchiseRepository,
            BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.franchiseRepository = franchiseRepository;
        this.branchMapper = branchMapper;
    }

    @Override
    public BranchResponse create(Long franchiseId, BranchCreateRequest request) {
        Franchise franchise = getFranchise(franchiseId);
        Branch branch = new Branch(request.getName(), franchise);
        return branchMapper.toResponse(branchRepository.save(branch));
    }

    @Override
    public List<BranchResponse> findAll() {
        return branchRepository.findAllSummaries();
    }

    @Override
    public BranchResponse findById(Long id) {
        Branch branch = getEntity(id);
        return branchMapper.toResponse(branch);
    }

    @Override
    public BranchResponse update(Long id, BranchUpdateRequest request) {
        Branch branch = getEntity(id);
        branch.setName(request.getName());
        return branchMapper.toResponse(branchRepository.save(branch));
    }

    private Branch getEntity(Long id) {
        return branchRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));   
    }

    private Franchise getFranchise(Long franchiseId) {
        return franchiseRepository.findById(franchiseId)
                    .orElseThrow(() -> new ResourceNotFoundException("Franchise not found with id: " + franchiseId));   
    }

}

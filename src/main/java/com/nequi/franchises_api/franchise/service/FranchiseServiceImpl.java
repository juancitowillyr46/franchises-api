package com.nequi.franchises_api.franchise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nequi.franchises_api.franchise.dto.FranchiseRequest;
import com.nequi.franchises_api.franchise.dto.FranchiseResponse;
import com.nequi.franchises_api.franchise.entity.Franchise;
import com.nequi.franchises_api.franchise.repository.FranchiseRepository;
import com.nequi.franchises_api.shared.exception.ResourceNotFoundException;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public FranchiseResponse create(FranchiseRequest request) {
        Franchise franchise = new Franchise(request.getName());
        Franchise franchiseSave = franchiseRepository.save(franchise);
        return toResponse(franchiseSave);
    }

    @Override
    public List<FranchiseResponse> findAll() {
        return franchiseRepository.findAll()
                    .stream()
                    .map(this::toResponse)
                    .toList();
    }

    @Override
    public FranchiseResponse findById(Long id) {
        Franchise franchise = getEntity(id);
        return toResponse(franchise);
    }

    @Override
    public FranchiseResponse update(Long id, FranchiseRequest request) {
        Franchise franchise = getEntity(id);
        franchise.setName(request.getName());
        return toResponse(franchiseRepository.save(franchise));
    }

    @Override
    public void delete(Long id) {
        Franchise franchise = getEntity(id);
        franchiseRepository.delete(franchise);
    }

    private FranchiseResponse toResponse(Franchise franchise) {
        return new FranchiseResponse(
                        franchise.getId(), 
                        franchise.getName()
                    );
    }

    private Franchise getEntity(Long id) {
        return franchiseRepository.findById(id)
                .orElseThrow(() -> 
                        new ResourceNotFoundException("Franchise not found"));
    }
}

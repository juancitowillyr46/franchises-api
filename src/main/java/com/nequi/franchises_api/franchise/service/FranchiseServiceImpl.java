package com.nequi.franchises_api.franchise.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nequi.franchises_api.franchise.dto.FranchiseRequest;
import com.nequi.franchises_api.franchise.dto.FranchiseResponse;
import com.nequi.franchises_api.franchise.dto.TopStockProductResponse;
import com.nequi.franchises_api.franchise.entity.Franchise;
import com.nequi.franchises_api.franchise.repository.FranchiseRepository;
import com.nequi.franchises_api.franchise.mapper.FranchiseMapper;
import com.nequi.franchises_api.product.repository.ProductRepository;
import com.nequi.franchises_api.shared.exception.ResourceNotFoundException;

import org.springframework.transaction.annotation.Transactional;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final ProductRepository productRepository;
    private final FranchiseMapper franchiseMapper;

    public FranchiseServiceImpl(
        FranchiseRepository franchiseRepository,
        ProductRepository productRepository,
        FranchiseMapper franchiseMapper) {
        this.franchiseRepository = franchiseRepository;
        this.productRepository = productRepository;
        this.franchiseMapper = franchiseMapper;
    }

    @Override
    public FranchiseResponse create(FranchiseRequest request) {
        Franchise franchise = new Franchise(request.getName());
        Franchise franchiseSave = franchiseRepository.save(franchise);
        return franchiseMapper.toResponse(franchiseSave);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FranchiseResponse> findAll(Pageable pageable) {
        return franchiseRepository.findAllSummaries(pageable);
    }

    @Override
    public FranchiseResponse findById(Long id) {
        Franchise franchise = getEntity(id);
        return franchiseMapper.toResponse(franchise);
    }

    @Override
    public FranchiseResponse update(Long id, FranchiseRequest request) {
        Franchise franchise = getEntity(id);
        franchise.setName(request.getName());
        return franchiseMapper.toResponse(franchiseRepository.save(franchise));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TopStockProductResponse> getTopStockProducts(Long franchiseId) {
        return productRepository.findTopStockProductsByFranchiseId(franchiseId);
    }


    private Franchise getEntity(Long id) {
        return franchiseRepository.findById(id)
                .orElseThrow(() -> 
                        new ResourceNotFoundException("Franchise not found"));
    }
}

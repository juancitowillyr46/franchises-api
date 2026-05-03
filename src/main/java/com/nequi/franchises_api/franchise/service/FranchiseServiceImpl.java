package com.nequi.franchises_api.franchise.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.nequi.franchises_api.branch.entity.Branch;
import com.nequi.franchises_api.franchise.dto.FranchiseRequest;
import com.nequi.franchises_api.franchise.dto.FranchiseResponse;
import com.nequi.franchises_api.franchise.dto.TopStockProductResponse;
import com.nequi.franchises_api.franchise.entity.Franchise;
import com.nequi.franchises_api.franchise.repository.FranchiseRepository;
import com.nequi.franchises_api.product.entity.Product;
import com.nequi.franchises_api.shared.exception.ResourceNotFoundException;

import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(readOnly = true)
    public List<TopStockProductResponse> getTopStockProducts(Long franchiseId) {

        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Franchise not found"));

        return franchise.getBranches()
                .stream()
                .map(this::mapTopStockProduct)
                .filter(Objects::nonNull)
                .toList();
    }

    private TopStockProductResponse mapTopStockProduct(Branch branch) {

        return branch.getProducts()
                .stream()
                .filter(product -> product.getStock() > 0)
                .max(Comparator.comparing(Product::getStock))
                .map(product -> new TopStockProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getStock(),
                        branch.getId(),
                        branch.getName()
                ))
                .orElse(null);
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

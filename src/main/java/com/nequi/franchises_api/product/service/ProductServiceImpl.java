package com.nequi.franchises_api.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nequi.franchises_api.branch.entity.Branch;
import com.nequi.franchises_api.branch.repository.BranchRepository;
import com.nequi.franchises_api.product.dto.ProductCreateRequest;
import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.dto.ProductStockUpdateRequest;
import com.nequi.franchises_api.product.dto.ProductUpdateRequest;
import com.nequi.franchises_api.product.entity.Product;
import com.nequi.franchises_api.product.mapper.ProductMapper;
import com.nequi.franchises_api.product.repository.ProductRepository;
import com.nequi.franchises_api.shared.exception.ResourceNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(
            ProductRepository productRepository, 
            BranchRepository branchRepository,
            ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse create(Long branchId, ProductCreateRequest request) {

        Branch branch = getBranch(branchId);
        Product product = new Product(
            request.getName(), 
            request.getStock(), 
            branch
        );
        productRepository.save(product);
        return productMapper.toResponse(product);
    }

    @Override
    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAllSummaries(pageable);
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = getEntity(id);
        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Product product = getEntity(id);
        product.setName(request.getName());
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        Product product = getEntity(id);
        productRepository.delete(product);
    }

    @Override
    public ProductResponse updateStock(Long id, ProductStockUpdateRequest request) {
        Product product = getEntity(id);
        product.setStock(request.getStock());
        return productMapper.toResponse(productRepository.save(product));
    }

    private Product getEntity(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    private Branch getBranch(Long branchId) {
        return branchRepository.findById(branchId)
                    .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + branchId));   
    }
}

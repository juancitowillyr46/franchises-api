package com.nequi.franchises_api.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nequi.franchises_api.branch.entity.Branch;
import com.nequi.franchises_api.branch.repository.BranchRepository;
import com.nequi.franchises_api.product.dto.BranchProductCreateRequest;
import com.nequi.franchises_api.product.dto.BranchProductResponse;
import com.nequi.franchises_api.product.dto.BranchProductStockUpdateRequest;
import com.nequi.franchises_api.product.entity.BranchProduct;
import com.nequi.franchises_api.product.entity.Product;
import com.nequi.franchises_api.product.mapper.BranchProductMapper;
import com.nequi.franchises_api.product.repository.BranchProductRepository;
import com.nequi.franchises_api.product.repository.ProductRepository;
import com.nequi.franchises_api.shared.exception.ResourceAlreadyExistsException;
import com.nequi.franchises_api.shared.exception.ResourceNotFoundException;

@Service
public class BranchProductServiceImpl implements BranchProductService {

    private final BranchProductRepository branchProductRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final BranchProductMapper branchProductMapper;

    public BranchProductServiceImpl(
            BranchProductRepository branchProductRepository,
            BranchRepository branchRepository,
            ProductRepository productRepository,
            BranchProductMapper branchProductMapper) {
        this.branchProductRepository = branchProductRepository;
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
        this.branchProductMapper = branchProductMapper;
    }

    @Override
    @Transactional
    public BranchProductResponse create(Long branchId, BranchProductCreateRequest request) {
        Branch branch = getBranch(branchId);
        Product product = getProduct(request.getProductId());

        if (branchProductRepository.existsByBranchIdAndProductId(branchId, request.getProductId())) {
            throw new ResourceAlreadyExistsException("Product already exists in branch with id: " + request.getProductId());
        }

        BranchProduct branchProduct = new BranchProduct(branch, product, request.getStock());
        return branchProductMapper.toResponse(branchProductRepository.save(branchProduct));
    }

    @Override
    @Transactional
    public BranchProductResponse updateStock(Long branchId, Long productId, BranchProductStockUpdateRequest request) {
        BranchProduct branchProduct = getBranchProduct(branchId, productId);
        branchProduct.setStock(request.getStock());
        return branchProductMapper.toResponse(branchProductRepository.save(branchProduct));
    }

    @Override
    @Transactional
    public void delete(Long branchId, Long productId) {
        BranchProduct branchProduct = getBranchProduct(branchId, productId);
        branchProductRepository.delete(branchProduct);
    }

    private BranchProduct getBranchProduct(Long branchId, Long productId) {
        return branchProductRepository.findByBranchIdAndProductId(branchId, productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product not found in branch with id: " + productId));
    }

    private Branch getBranch(Long branchId) {
        return branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + branchId));
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
    }
}

package com.nequi.franchises_api.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nequi.franchises_api.branch.entity.Branch;
import com.nequi.franchises_api.branch.repository.BranchRepository;
import com.nequi.franchises_api.product.dto.ProductCreateRequest;
import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.dto.ProductStockUpdateRequest;
import com.nequi.franchises_api.product.dto.ProductUpdateRequest;
import com.nequi.franchises_api.product.entity.Product;
import com.nequi.franchises_api.product.repository.ProductRepository;
import com.nequi.franchises_api.shared.exception.ResourceNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    public ProductServiceImpl(
            ProductRepository productRepository, 
            BranchRepository branchRepository) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public ProductResponse create(ProductCreateRequest request) {

        Branch branch = getBranch(request.getBranchId());
        Product product = new Product(
            request.getName(), 
            request.getStock(), 
            branch
        );
        productRepository.save(product);
        return toResponse(product);
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = getEntity(id);
        return toResponse(product);
    }

    @Override
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Product product = getEntity(id);
        product.setName(request.getName());
        return toResponse(productRepository.save(product));
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
        return toResponse(productRepository.save(product));
    }

    private Product getEntity(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getStock(),
            product.getBranch().getId()
        );
    }

    private Branch getBranch(Long branchId) {
        return branchRepository.findById(branchId)
                    .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + branchId));   
    }
}

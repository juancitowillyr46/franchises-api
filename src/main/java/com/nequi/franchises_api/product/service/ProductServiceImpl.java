package com.nequi.franchises_api.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nequi.franchises_api.product.dto.ProductCreateRequest;
import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.dto.ProductUpdateRequest;
import com.nequi.franchises_api.product.entity.Product;
import com.nequi.franchises_api.product.mapper.ProductMapper;
import com.nequi.franchises_api.product.repository.BranchProductRepository;
import com.nequi.franchises_api.product.repository.ProductRepository;
import com.nequi.franchises_api.shared.exception.ResourceAlreadyExistsException;
import com.nequi.franchises_api.shared.exception.ResourceNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BranchProductRepository branchProductRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(
            ProductRepository productRepository,
            BranchProductRepository branchProductRepository,
            ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.branchProductRepository = branchProductRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductResponse create(ProductCreateRequest request) {
        if (productRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ResourceAlreadyExistsException("Product already exists with name: " + request.getName());
        }

        Product product = new Product(request.getName());
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAllSummaries();
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = getEntity(id);
        return productMapper.toResponse(product);
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest request) {
        Product product = getEntity(id);
        if (productRepository.existsByNameIgnoreCase(request.getName())
                && !product.getName().equalsIgnoreCase(request.getName())) {
            throw new ResourceAlreadyExistsException("Product already exists with name: " + request.getName());
        }
        product.setName(request.getName());
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Product product = getEntity(id);
        branchProductRepository.deleteByProductId(product.getId());
        productRepository.delete(product);
    }

    private Product getEntity(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
}

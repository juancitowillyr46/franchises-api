package com.nequi.franchises_api.product.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.dto.ProductStockUpdateRequest;
import com.nequi.franchises_api.product.dto.ProductUpdateRequest;
import com.nequi.franchises_api.product.service.ProductService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Operations for products.")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "List products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paginated product list")
    })
    public Page<ProductResponse> findAll(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ProductResponse findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ProductResponse update(
            @PathVariable Long id, 
            @Valid @RequestBody ProductUpdateRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a product")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Update product stock")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product stock updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ProductResponse updateStock(
            @PathVariable Long id, 
            @Valid @RequestBody ProductStockUpdateRequest request) {
        return productService.updateStock(id, request);
    }
}

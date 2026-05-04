package com.nequi.franchises_api.product.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.dto.ProductCreateRequest;
import com.nequi.franchises_api.product.dto.ProductUpdateRequest;
import com.nequi.franchises_api.product.service.ProductService;
import com.nequi.franchises_api.shared.response.StandardResponse;

import jakarta.validation.Valid;

import java.util.List;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a product catalog entry")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "409", description = "Product already exists")
    })
    public StandardResponse<ProductResponse> create(
            @Valid @RequestBody ProductCreateRequest request) {
        return StandardResponse.success("Product created successfully", productService.create(request));
    }

    @GetMapping
    @Operation(summary = "List products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product list returned")
    })
    public StandardResponse<List<ProductResponse>> findAll() {
        return StandardResponse.success("Products retrieved successfully", productService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public StandardResponse<ProductResponse> findById(@PathVariable Long id) {
        return StandardResponse.success("Product found successfully", productService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public StandardResponse<ProductResponse> update(
            @PathVariable Long id, 
            @Valid @RequestBody ProductUpdateRequest request) {
        return StandardResponse.success("Product updated successfully", productService.update(id, request));
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
}

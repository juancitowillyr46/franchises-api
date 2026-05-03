package com.nequi.franchises_api.branch.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nequi.franchises_api.branch.dto.BranchResponse;
import com.nequi.franchises_api.branch.dto.BranchUpdateRequest;
import com.nequi.franchises_api.branch.service.BranchService;
import com.nequi.franchises_api.product.dto.ProductCreateRequest;
import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.service.ProductService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/branches")
@Tag(name = "Branches", description = "Operations for branches and nested product creation.")
public class BranchController {

    private final BranchService branchService;
    private final ProductService productService;

    public BranchController(
            BranchService branchService,
            ProductService productService) {
        this.branchService = branchService;
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "List branches")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paginated branch list")
    })
    public Page<BranchResponse> findAll(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return branchService.findAll(pageable);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get a branch by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Branch found"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    public BranchResponse findById(
        @PathVariable Long id
    ) {
        return branchService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a branch")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Branch updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    public BranchResponse update(
            @PathVariable Long id,
            @Valid @RequestBody BranchUpdateRequest request) {
        return branchService.update(id, request);
    }

    @PostMapping("/{id}/products")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a product within a branch")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    public ProductResponse createProduct(
            @Parameter(description = "Branch identifier")
            @PathVariable Long id,
            @Valid @RequestBody ProductCreateRequest request) {
        return productService.create(id, request);
    }

}

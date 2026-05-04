package com.nequi.franchises_api.branch.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import com.nequi.franchises_api.product.dto.BranchProductCreateRequest;
import com.nequi.franchises_api.product.dto.BranchProductResponse;
import com.nequi.franchises_api.product.dto.BranchProductStockUpdateRequest;
import com.nequi.franchises_api.product.service.BranchProductService;
import com.nequi.franchises_api.shared.response.StandardResponse;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/branches")
@Tag(name = "Branches", description = "Operations for branches and branch-product inventory management.")
public class BranchController {

    private final BranchService branchService;
    private final BranchProductService branchProductService;

    public BranchController(
            BranchService branchService,
            BranchProductService branchProductService) {
        this.branchService = branchService;
        this.branchProductService = branchProductService;
    }

    @GetMapping
    @Operation(summary = "List branches")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Branch list returned")
    })
    public StandardResponse<List<BranchResponse>> findAll() {
        return StandardResponse.success("Branches retrieved successfully", branchService.findAll());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get a branch by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Branch found"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    public StandardResponse<BranchResponse> findById(
        @PathVariable Long id
    ) {
        return StandardResponse.success("Branch found successfully", branchService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a branch")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Branch updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    public StandardResponse<BranchResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody BranchUpdateRequest request) {
        return StandardResponse.success("Branch updated successfully", branchService.update(id, request));
    }

    @PostMapping("/{id}/products")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Assign a product to a branch")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Branch product created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Branch or product not found"),
            @ApiResponse(responseCode = "409", description = "Product already exists in branch")
    })
    public StandardResponse<BranchProductResponse> createProduct(
            @Parameter(description = "Branch identifier")
            @PathVariable Long id,
            @Valid @RequestBody BranchProductCreateRequest request) {
        return StandardResponse.success("Product assigned successfully", branchProductService.create(id, request));
    }

    @PatchMapping("/{branchId}/products/{productId}/stock")
    @Operation(summary = "Update branch product stock")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Branch product stock updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Branch product not found")
    })
    public StandardResponse<BranchProductResponse> updateStock(
            @PathVariable Long branchId,
            @PathVariable Long productId,
            @Valid @RequestBody BranchProductStockUpdateRequest request) {
        return StandardResponse.success(
                "Branch product stock updated successfully",
                branchProductService.updateStock(branchId, productId, request));
    }

    @DeleteMapping("/{branchId}/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove a product from a branch")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Branch product deleted"),
            @ApiResponse(responseCode = "404", description = "Branch product not found")
    })
    public void deleteProduct(
            @PathVariable Long branchId,
            @PathVariable Long productId) {
        branchProductService.delete(branchId, productId);
    }
}

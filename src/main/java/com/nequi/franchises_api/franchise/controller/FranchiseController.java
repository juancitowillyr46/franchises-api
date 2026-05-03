package com.nequi.franchises_api.franchise.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nequi.franchises_api.branch.dto.BranchCreateRequest;
import com.nequi.franchises_api.branch.dto.BranchResponse;
import com.nequi.franchises_api.branch.service.BranchService;
import com.nequi.franchises_api.franchise.dto.FranchiseRequest;
import com.nequi.franchises_api.franchise.dto.FranchiseResponse;
import com.nequi.franchises_api.franchise.dto.TopStockProductResponse;
import com.nequi.franchises_api.franchise.service.FranchiseService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.data.domain.Pageable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/franchises")
@Tag(name = "Franchises", description = "Operations for franchises and nested branch creation.")
public class FranchiseController {

    private final FranchiseService franchiseService;
    private final BranchService branchService;

    public FranchiseController(
            FranchiseService franchiseService,
            BranchService branchService) {
        this.franchiseService = franchiseService;
        this.branchService = branchService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a franchise")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Franchise created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public FranchiseResponse create(
        @Valid @RequestBody FranchiseRequest request
    ) {
        return franchiseService.create(request);
    }

    @PostMapping("/{id}/branches")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a branch within a franchise")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Branch created"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Franchise not found")
    })
    public BranchResponse createBranch(
            @Parameter(description = "Franchise identifier")
            @PathVariable Long id,
            @Valid @RequestBody BranchCreateRequest request) {
        return branchService.create(id, request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "List franchises")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paginated franchise list")
    })
    public Page<FranchiseResponse> getAll(
            @ParameterObject
            @PageableDefault(size = 10) Pageable pageable) {
        return franchiseService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a franchise by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Franchise found"),
            @ApiResponse(responseCode = "404", description = "Franchise not found")
    })
    public FranchiseResponse findById(@PathVariable Long id) {
        return franchiseService.findById(id);
    }
    
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a franchise")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Franchise updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "Franchise not found")
    })
    public FranchiseResponse update(@PathVariable Long id, 
        @Valid @RequestBody FranchiseRequest request
    ) {
        return franchiseService.update(id, request);
    }
    
    @GetMapping("/{id}/top-stock-products")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get top stock product by branch for a franchise")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Top stock list returned"),
            @ApiResponse(responseCode = "404", description = "Franchise not found")
    })
    public List<TopStockProductResponse> getTopStockProducts(
            @PathVariable Long id) {

        return franchiseService.getTopStockProducts(id);
    }
}

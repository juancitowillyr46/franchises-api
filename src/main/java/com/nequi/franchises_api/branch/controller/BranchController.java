package com.nequi.franchises_api.branch.controller;

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

import com.nequi.franchises_api.branch.dto.BranchCreateRequest;
import com.nequi.franchises_api.branch.dto.BranchResponse;
import com.nequi.franchises_api.branch.dto.BranchUpdateRequest;
import com.nequi.franchises_api.branch.service.BranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BranchResponse create(
            @Valid @RequestBody BranchCreateRequest request) {
        return branchService.create(request);
    }

    @GetMapping
    public Page<BranchResponse> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return branchService.findAll(pageable);
    }
    
    @GetMapping(("/{id}"))
    public BranchResponse findById(
        @PathVariable Long id
    ) {
        return branchService.findById(id);
    }

    @PutMapping("/{id}")
    public BranchResponse update(
            @PathVariable Long id,
            @Valid @RequestBody BranchUpdateRequest request) {
        return branchService.update(id, request);
    }

}

package com.nequi.franchises_api.franchise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nequi.franchises_api.franchise.dto.FranchiseRequest;
import com.nequi.franchises_api.franchise.dto.FranchiseResponse;
import com.nequi.franchises_api.franchise.entity.Franchise;
import com.nequi.franchises_api.franchise.service.FranchiseService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public FranchiseResponse create(
        @Valid @RequestBody FranchiseRequest request
    ) {
        return franchiseService.create(request);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FranchiseResponse> getAll() {
        return franchiseService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FranchiseResponse finById(@PathVariable Long id) {
        return franchiseService.findById(id);
    }
    
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FranchiseResponse update(@PathVariable Long id, 
        @Valid @RequestBody FranchiseRequest request
    ) {
        return franchiseService.update(id, request);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        franchiseService.delete(id);
    }
}

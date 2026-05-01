package com.nequi.franchises_api.franchise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nequi.franchises_api.franchise.entity.Franchise;
import com.nequi.franchises_api.franchise.service.FranchiseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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
    public String create(@RequestBody Franchise franchise) {
        return franchiseService.create(franchise.getName()).getName();
    }

    @GetMapping()
    public List<Franchise> getAll() {
        return franchiseService.findAll();
    }

    @GetMapping("/{id}")
    public Franchise finById(@PathVariable Long id) {
        return franchiseService.findById(id);
    }
    
    
    @PutMapping("/{id}")
    public Franchise update(@PathVariable Long id, @RequestBody Franchise franchise) {
        return franchiseService.update(id, franchise.getName());
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        franchiseService.delete(id);
    }
}

package com.nequi.franchises_api.branch.entity;

import com.nequi.franchises_api.franchise.entity.Franchise;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "franchise_id", nullable = false)
    private Franchise franchise;

    public Branch() {

    }

    public Branch(String name, Franchise franchise) {
        this.name = name;
        this.franchise = franchise;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

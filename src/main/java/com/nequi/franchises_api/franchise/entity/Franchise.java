package com.nequi.franchises_api.franchise.entity;

import java.util.ArrayList;
import java.util.List;

import com.nequi.franchises_api.branch.entity.Branch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "franchises")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "franchise")
    private List<Branch> branches = new ArrayList<>();

    public Franchise() {
    }
    
    public Franchise(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Branch> getBranches() {
        return branches;
    }
}

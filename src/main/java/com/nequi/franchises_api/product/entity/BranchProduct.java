package com.nequi.franchises_api.product.entity;

import com.nequi.franchises_api.branch.entity.Branch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "branch_products",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_branch_products_branch_product",
                columnNames = {"branch_id", "product_id"}
        )
)
public class BranchProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer stock;

    public BranchProduct() {
    }

    public BranchProduct(Branch branch, Product product, Integer stock) {
        this.branch = branch;
        this.product = product;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public Branch getBranch() {
        return branch;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

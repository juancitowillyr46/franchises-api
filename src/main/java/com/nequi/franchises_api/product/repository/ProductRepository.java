package com.nequi.franchises_api.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nequi.franchises_api.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

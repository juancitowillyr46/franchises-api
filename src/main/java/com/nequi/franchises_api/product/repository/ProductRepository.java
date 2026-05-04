package com.nequi.franchises_api.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nequi.franchises_api.product.entity.Product;
import com.nequi.franchises_api.product.dto.ProductResponse;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Product> findByNameIgnoreCase(String name);

    @Query("""
            select new com.nequi.franchises_api.product.dto.ProductResponse(
                p.id,
                p.name
            )
            from Product p
            order by p.id asc
            """)
    List<ProductResponse> findAllSummaries();

}

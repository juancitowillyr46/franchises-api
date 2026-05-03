package com.nequi.franchises_api.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nequi.franchises_api.product.dto.ProductResponse;
import com.nequi.franchises_api.product.entity.Product;
import com.nequi.franchises_api.franchise.dto.TopStockProductResponse;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            select new com.nequi.franchises_api.product.dto.ProductResponse(
                p.id,
                p.name,
                p.stock,
                p.branch.id
            )
            from Product p
            """)
    Page<ProductResponse> findAllSummaries(Pageable pageable);

    @Query("""
            select new com.nequi.franchises_api.franchise.dto.TopStockProductResponse(
                p.id,
                p.name,
                p.stock,
                b.id,
                b.name
            )
            from Product p
            join p.branch b
            where b.franchise.id = :franchiseId
              and p.stock = (
                select max(p2.stock)
                from Product p2
                where p2.branch = b
              )
              and p.id = (
                select min(p3.id)
                from Product p3
                where p3.branch = b
                  and p3.stock = p.stock
              )
            order by b.id asc, p.id asc
            """)
    List<TopStockProductResponse> findTopStockProductsByFranchiseId(@Param("franchiseId") Long franchiseId);

}

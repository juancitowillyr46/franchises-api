package com.nequi.franchises_api.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nequi.franchises_api.franchise.dto.TopStockProductResponse;
import com.nequi.franchises_api.product.entity.BranchProduct;

public interface BranchProductRepository extends JpaRepository<BranchProduct, Long> {

    boolean existsByBranchIdAndProductId(Long branchId, Long productId);

    Optional<BranchProduct> findByBranchIdAndProductId(Long branchId, Long productId);

    void deleteByBranchIdAndProductId(Long branchId, Long productId);

    void deleteByProductId(Long productId);

    @Query("""
            select new com.nequi.franchises_api.franchise.dto.TopStockProductResponse(
                p.id,
                p.name,
                bp.stock,
                b.id,
                b.name
            )
            from BranchProduct bp
            join bp.branch b
            join bp.product p
            where b.franchise.id = :franchiseId
              and bp.stock = (
                select max(bp2.stock)
                from BranchProduct bp2
                where bp2.branch = b
              )
              and bp.id = (
                select min(bp3.id)
                from BranchProduct bp3
                where bp3.branch = b
                  and bp3.stock = bp.stock
              )
            order by b.id asc, p.id asc
            """)
    List<TopStockProductResponse> findTopStockProductsByFranchiseId(@Param("franchiseId") Long franchiseId);
}

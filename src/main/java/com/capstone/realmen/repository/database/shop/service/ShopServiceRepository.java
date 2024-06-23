package com.capstone.realmen.repository.database.shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;

@Repository
public interface ShopServiceRepository extends JpaRepository<ShopServiceEntity, Long> {
    @Query("""
        SELECT ss
        FROM ShopServiceEntity ss     
        WHERE :#{#searchCriteria.hasSearchEmpty()} = TRUE
            OR LOWER(ss.shopServiceName) LIKE %:#{#searchCriteria.search()}%
        AND (:#{#searchCriteria.hasShopServicePriceRangeEmpty()} = TRUE
            OR ss.shopServicePrice BETWEEN :#{#searchCriteria.searchFrom()} AND :#{#searchCriteria.searchTo()})   
    """)
    Page<ShopServiceEntity> findAll(ShopServiceSearchCriteria searchCriteria, Pageable pageable);
}

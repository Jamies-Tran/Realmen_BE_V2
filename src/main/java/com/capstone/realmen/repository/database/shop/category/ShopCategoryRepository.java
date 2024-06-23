package com.capstone.realmen.repository.database.shop.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.service.shop.category.data.ShopCategorySearchCriteria;

@Repository
public interface ShopCategoryRepository extends JpaRepository<ShopCategoryEntity, Long> {

    @Query("""
        SELECT sc
        FROM ShopCategoryEntity sc
        WHERE :#{#searchCategory.hasSearchEmpty()} = TRUE
            OR sc.shopCategoryCode LIKE %:#{#searchCategory.search()}%
            OR sc.shopCategoryName LIKE %:#{#searchCategory.search()}%

    """)
    Page<ShopCategoryEntity> findAll(ShopCategorySearchCriteria searchCriteria, Pageable pageable);
    
    Boolean existsByShopCategoryCode(String shopCategoryCode);
}

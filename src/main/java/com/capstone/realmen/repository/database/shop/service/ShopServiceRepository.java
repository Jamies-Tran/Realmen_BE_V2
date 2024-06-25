package com.capstone.realmen.repository.database.shop.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.data.dao.shop.service.ShopServiceDAO;
import com.capstone.realmen.service.shop.service.data.ShopServiceSearchCriteria;

@Repository
public interface ShopServiceRepository extends JpaRepository<ShopServiceEntity, Long> {
    @Query("""
        SELECT 
            ss.shopServiceId AS shopServiceId,
            ss.shopServiceName AS shopServiceName,
            ss.shopServicePrice AS shopServicePrice,
            ss.shopServiceThumbnail AS shopServiceThumbnail,
            sc.shopCategoryId AS shopCategoryId,
            sc.shopCategoryCode AS shopCategoryCode,
            sc.shopCategoryName AS shopCategoryName
        FROM ShopServiceEntity ss
        INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
        WHERE :#{#searchCriteria.hasSearchEmpty()} = TRUE
            OR LOWER(ss.shopServiceName) LIKE %:#{#searchCriteria.search()}%
        AND (:#{#searchCriteria.hasShopServicePriceRangeEmpty()} = TRUE
            OR ss.shopServicePrice BETWEEN :#{#searchCriteria.priceFrom()} AND :#{#searchCriteria.priceTo()})   
    """)
    Page<ShopServiceDAO> findAll(ShopServiceSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
        SELECT 
            ss.shopServiceId AS shopServiceId,
            ss.shopServiceName AS shopServiceName,
            ss.shopServicePrice AS shopServicePrice,
            ss.shopServiceThumbnail AS shopServiceThumbnail,
            sc.shopCategoryId AS shopCategoryId,
            sc.shopCategoryCode AS shopCategoryCode,
            sc.shopCategoryName AS shopCategoryName
        FROM ShopServiceEntity ss
        INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
        WHERE ss.shopServiceId = :shopServiceId
    """)
    Optional<ShopServiceDAO> findByShopServiceId(Long shopServiceId);
}

package com.capstone.realmen.repository.database.shop.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopCategoryRepository extends JpaRepository<ShopCategoryEntity, Long> {
    Boolean existsByShopCategoryCode(String shopCategoryCode);
}

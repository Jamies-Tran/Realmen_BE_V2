package com.capstone.realmen.repository.database.shop.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopServiceRepository extends JpaRepository<ShopServiceEntity, Long> {
    
}

package com.capstone.realmen.repository.database.shop.service.display;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDisplayRepository extends JpaRepository<ServiceDisplayEntity, Long> {
    
}

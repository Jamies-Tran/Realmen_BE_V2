package com.capstone.realmen.repository.database.branch.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchServiceRepository extends JpaRepository<BranchServiceEntity, Long>{
    
}

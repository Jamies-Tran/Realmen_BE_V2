package com.capstone.realmen.repository.database.branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchRepository extends JpaRepository<BranchEntity, Long> {
    
}

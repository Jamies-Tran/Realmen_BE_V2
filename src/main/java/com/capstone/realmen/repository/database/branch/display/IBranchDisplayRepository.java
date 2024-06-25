package com.capstone.realmen.repository.database.branch.display;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchDisplayRepository extends JpaRepository<BranchDisplayEntity, Long>{
    
}

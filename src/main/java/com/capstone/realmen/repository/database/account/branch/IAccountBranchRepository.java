package com.capstone.realmen.repository.database.account.branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountBranchRepository extends JpaRepository<AccountBranchEntity, Long>{
    
}

package com.capstone.realmen.repository.database.account.plans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDailyPlanAccountRepository extends JpaRepository<DailyPlanAccountEntity, Long>{
    
}

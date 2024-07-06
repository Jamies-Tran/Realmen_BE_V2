package com.capstone.realmen.repository.database.shop.service.plans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDailyPlanServiceRepository extends JpaRepository<DailyPlanServiceEntity, Long> {

}

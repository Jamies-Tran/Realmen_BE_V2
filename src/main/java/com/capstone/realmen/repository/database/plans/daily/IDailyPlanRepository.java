package com.capstone.realmen.repository.database.plans.daily;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDailyPlanRepository extends JpaRepository<DailyPlanEntity, Long> {
    List<DailyPlanEntity> findAllByWeeklyPlanId(Long weeklyPlanId);
}

package com.capstone.realmen.repository.database.plans.daily;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDailyPlanRepository extends JpaRepository<DailyPlanEntity, Long> {
    List<DailyPlanEntity> findAllByWeeklyPlanId(Long weeklyPlanId);

    @Query("""
        SELECT
            MAX (dp.weeklyPlanId)
        FROM DailyPlanEntity dp        
    """)
    Long getNewestWeeklyPlanId();

    @Query("""
            SELECT dp
            FROM DailyPlanEntity dp
            INNER JOIN DailyPlanAccountEntity dpa ON dp.dailyPlanId = dpa.dailyPlanId
            INNER JOIN WeeklyPlanEntity wp ON dp.weeklyPlanId = wp.weeklyPlanId
            WHERE dpa.accountId = :accountId
                AND wp.branchId = :branchId
                
            """)
    List<DailyPlanEntity> findAllByStaffIdAndBranchId(Long accountId, Long branchId);
}

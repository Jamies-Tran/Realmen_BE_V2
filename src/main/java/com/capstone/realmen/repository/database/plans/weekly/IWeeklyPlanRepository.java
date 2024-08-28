package com.capstone.realmen.repository.database.plans.weekly;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.service.plans.data.WeeklyPlanSearchCriteria;

@Repository
public interface IWeeklyPlanRepository extends JpaRepository<WeeklyPlanEntity, Long> {

    @Query("""
            SELECT wp
            FROM WeeklyPlanEntity wp
            INNER JOIN DailyPlanEntity dp ON wp.weeklyPlanId = dp.weeklyPlanId
            WHERE (:#{#searchCriteria.hasSearchEmpty()} = TRUE
                OR LOWER(wp.weeklyPlanName) LIKE %:#{#searchCriteria.search()}%)
            AND (:#{#searchCriteria.hasTimeRangeEmpty()} = TRUE
                OR dp.date BETWEEN :#{#searchCriteria.timeFrom()} AND :#{#searchCriteria.timeTo()})
            """)
    Page<WeeklyPlanEntity> findAll(WeeklyPlanSearchCriteria searchCriteria, Pageable pageable);
}

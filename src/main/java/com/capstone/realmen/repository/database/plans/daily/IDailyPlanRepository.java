package com.capstone.realmen.repository.database.plans.daily;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchByField;
import com.capstone.realmen.service.plans.others.daily.plan.data.DailyPlanSearchCriteria;

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
            INNER JOIN WeeklyPlanEntity wp ON dp.weeklyPlanId = wp.weeklyPlanId
            WHERE (dp.weeklyPlanId IN :#{#searchByField.weeklyPlanIds()})
                AND (:#{#searchByField.hasStatusEmpty()} = TRUE
                    OR dp.dailyPlanStatusCode = :#{#searchByField.status()})
            """)
    List<DailyPlanEntity> findByWeeklyPlanIds(DailyPlanSearchByField searchByField);

    @Query("""
            SELECT dp
            FROM DailyPlanEntity dp
            INNER JOIN DailyPlanAccountEntity dpa ON dp.dailyPlanId = dpa.dailyPlanId
            INNER JOIN WeeklyPlanEntity wp ON dp.weeklyPlanId = wp.weeklyPlanId
            WHERE (:#{#searchCriteria.hasTimeRangeEmpty()} = TRUE
                OR dp.date BETWEEN :#{#searchCriteria.timeFrom()} AND :#{#searchCriteria.timeTo()})
            AND (:#{#searchCriteria.hasWeeklyPlanIdEmpty()} = TRUE
                OR dp.weeklyPlanId = :#{#searchCriteria.weeklyPlanId()})
            AND (:#{#searchCriteria.hasAccountIdEmpty()} = TRUE
                OR dpa.accountId = :#{#searchCriteria.accountId()})

            """)
    Page<DailyPlanEntity> findAll(DailyPlanSearchCriteria searchCriteria, Pageable pageable);
}

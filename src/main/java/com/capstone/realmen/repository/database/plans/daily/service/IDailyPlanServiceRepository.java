package com.capstone.realmen.repository.database.plans.daily.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.data.dao.plans.daily.service.DailyPlanServiceDAO;
import com.capstone.realmen.service.plans.others.daily.plan.others.service.data.DailyPlanServiceSearchCriteria;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDailyPlanServiceRepository extends JpaRepository<DailyPlanServiceEntity, Long> {

    @Query("""
            SELECT
                dps.dailyPlanServiceId AS dailyPlanServiceId,
                dps.dailyPlanId AS dailyPlanId,
                wp.weeklyPlanId AS weeklyPlanId,
                bs.branchId AS branchId,
                ss.shopServiceId AS shopServiceId,
                ss.shopServiceName AS shopServiceName,
                COALESCE(bs.branchServicePrice, 0) AS branchServicePrice,
                ss.shopServicePrice AS shopServicePrice,
                sc.shopCategoryCode AS categoryCode,
                sc.shopCategoryName AS categoryName,
                sc.serviceAssignmentCode AS serviceAssignmentCode,
                sc.serviceAssignmentName AS serviceAssignmentName,
                dps.estimateDuration AS estimateDuration,
                dps.durationUnitCode AS durationUnitCode,
                dps.durationUnitName AS durationUnitName
            FROM DailyPlanServiceEntity dps
                INNER JOIN BranchServiceEntity bs ON dps.branchServiceId = bs.branchServiceId
                INNER JOIN ShopServiceEntity ss ON bs.shopServiceId = ss.shopServiceId
                INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
                INNER JOIN DailyPlanEntity dp ON dps.dailyPlanId = dp.dailyPlanId
                INNER JOIN WeeklyPlanEntity wp ON wp.weeklyPlanId = dp.weeklyPlanId
            WHERE dps.dailyPlanId = :dailyPlanId
                """)
    List<DailyPlanServiceDAO> findAllByDailyPlanId(Long dailyPlanId);

    @Query("""
            SELECT
                dps.dailyPlanServiceId AS dailyPlanServiceId,
                dps.dailyPlanId AS dailyPlanId,
                wp.weeklyPlanId AS weeklyPlanId,
                bs.branchId AS branchId,
                ss.shopServiceId AS shopServiceId,
                ss.shopServiceName AS shopServiceName,
                COALESCE(bs.branchServicePrice, 0) AS branchServicePrice,
                ss.shopServicePrice AS shopServicePrice,
                sc.shopCategoryCode AS categoryCode,
                sc.shopCategoryName AS categoryName,
                sc.serviceAssignmentCode AS serviceAssignmentCode,
                sc.serviceAssignmentName AS serviceAssignmentName,
                dps.estimateDuration AS estimateDuration,
                dps.durationUnitCode AS durationUnitCode,
                dps.durationUnitName AS durationUnitName
            FROM DailyPlanServiceEntity dps
                INNER JOIN BranchServiceEntity bs ON bs.branchServiceId = dps.branchServiceId
                INNER JOIN ShopServiceEntity ss ON bs.shopServiceId = ss.shopServiceId
                INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
                INNER JOIN DailyPlanEntity dp ON dps.dailyPlanId = dp.dailyPlanId
                INNER JOIN WeeklyPlanEntity wp ON wp.weeklyPlanId = dp.weeklyPlanId
                    AND bs.branchId = wp.branchId
            WHERE dps.dailyPlanId IN :dailyPlanIds
                """)
    List<DailyPlanServiceDAO> findAllByDailyPlanIdIn(List<Long> dailyPlanIds);

    @Query("""
            SELECT
                dps.dailyPlanServiceId AS dailyPlanServiceId,
                dps.dailyPlanId AS dailyPlanId,
                wp.weeklyPlanId AS weeklyPlanId,
                wp.branchId AS branchId,
                dps.branchServiceId AS branchServiceId,
                ss.shopServiceId AS shopServiceId,
                ss.shopServiceName AS shopServiceName,
                COALESCE(bs.branchServicePrice, 0) AS branchServicePrice,
                ss.shopServicePrice AS shopServicePrice,
                sc.shopCategoryCode AS categoryCode,
                sc.shopCategoryName AS categoryName,
                sc.serviceAssignmentCode AS serviceAssignmentCode,
                sc.serviceAssignmentName AS serviceAssignmentName,
                dps.estimateDuration AS estimateDuration,
                dps.durationUnitCode AS durationUnitCode,
                dps.durationUnitName AS durationUnitName
            FROM DailyPlanServiceEntity dps
                INNER JOIN BranchServiceEntity bs ON bs.branchServiceId = dps.branchServiceId
                INNER JOIN ShopServiceEntity ss ON bs.shopServiceId = ss.shopServiceId
                INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
                INNER JOIN DailyPlanEntity dp ON dps.dailyPlanId = dp.dailyPlanId
                INNER JOIN WeeklyPlanEntity wp ON wp.weeklyPlanId = dp.weeklyPlanId
            WHERE (:#{#searchCriteria.hasBranchIdEmpty()} = TRUE
                OR wp.branchId = :#{#searchCriteria.branchId()})
            AND (:#{#searchCriteria.hasDailyPlanIdEmpty()} = TRUE
                OR dp.dailyPlanId = :#{#searchCriteria.dailyPlanId()})
            AND (:#{#searchCriteria.hasDailyPlanServiceIdsEmpty()} = TRUE
                OR dps.dailyPlanServiceId IN :#{#searchCriteria.dailyPlanServiceIds()})
            AND (:#{#searchCriteria.hasServiceAssignmentCodeEmpty()} = TRUE
                OR sc.serviceAssignmentCode IN :#{#searchCriteria.serviceAssignmentCodes()})
            """)
    Page<DailyPlanServiceDAO> findAll(DailyPlanServiceSearchCriteria searchCriteria, Pageable pageable);

    void deleteAllByDailyPlanId(Long dailyPlanId);

    @Query("""
            SELECT
                dps.dailyPlanServiceId AS dailyPlanServiceId,
                dps.dailyPlanId AS dailyPlanId,
                wp.weeklyPlanId AS weeklyPlanId,
                bs.branchId AS branchId,
                ss.shopServiceId AS shopServiceId,
                dps.branchServiceId AS branchServiceId,
                ss.shopServiceName AS shopServiceName,
                COALESCE(bs.branchServicePrice, 0) AS branchServicePrice,
                ss.shopServicePrice AS shopServicePrice,
                sc.shopCategoryCode AS categoryCode,
                sc.shopCategoryName AS categoryName,
                sc.serviceAssignmentCode AS serviceAssignmentCode,
                sc.serviceAssignmentName AS serviceAssignmentName,
                dps.estimateDuration AS estimateDuration,
                dps.durationUnitCode AS durationUnitCode,
                dps.durationUnitName AS durationUnitName
            FROM DailyPlanServiceEntity dps
                INNER JOIN BranchServiceEntity bs ON dps.branchServiceId = bs.branchServiceId
                INNER JOIN ShopServiceEntity ss ON bs.shopServiceId = ss.shopServiceId
                INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
                INNER JOIN DailyPlanEntity dp ON dps.dailyPlanId = dp.dailyPlanId
                INNER JOIN WeeklyPlanEntity wp ON wp.weeklyPlanId = dp.weeklyPlanId
            WHERE dps.dailyPlanServiceId = :dailyPlanServiceId
            """)
    Optional<DailyPlanServiceDAO> findByDailyPlanServiceId(Long dailyPlanServiceId);
}

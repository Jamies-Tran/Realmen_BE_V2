package com.capstone.realmen.repository.database.shop.service.plans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.data.dao.plans.daily.service.DailyPlanServiceDAO;

import java.util.List;

@Repository
public interface IDailyPlanServiceRepository extends JpaRepository<DailyPlanServiceEntity, Long> {

    @Query("""
            SELECT
                dps.dailyPlanServiceId AS dailyPlanServiceId,
                dps.dailyPlanId AS dailyPlanId,
                ss.shopServiceId AS shopServiceId,
                ss.shopServiceName AS shopServiceName,
                COALESCE(bs.branchServicePrice, ss.shopServicePrice) AS shopServicePrice,
                sc.shopCategoryCode AS categoryCode,
                sc.shopCategoryName AS categoryName
            FROM DailyPlanServiceEntity dps
                INNER JOIN ShopServiceEntity ss ON dps.shopServiceId = ss.shopServiceId
                INNER JOIN ShopCategoryEntity sc ON ss.shopCategoryId = sc.shopCategoryId
                INNER JOIN DailyPlanEntity dp ON dps.dailyPlanId = dp.dailyPlanId
                INNER JOIN WeeklyPlanEntity wp ON wp.weeklyPlanId = dp.weeklyPlanId
                LEFT JOIN BranchServiceEntity bs ON ss.shopServiceId = bs.shopServiceId
                    AND bs.branchId = wp.branchId
            WHERE dps.dailyPlanId IN :dailyPlanId
                """)
    List<DailyPlanServiceDAO> findAllByDailyPlanId(Long dailyPlanId);

    void deleteAllByDailyPlanId(Long dailyPlanId);
}

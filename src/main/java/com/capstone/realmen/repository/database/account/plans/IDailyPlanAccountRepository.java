package com.capstone.realmen.repository.database.account.plans;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.data.dao.plans.daily.account.DailyPlanAccountDAO;

@Repository
public interface IDailyPlanAccountRepository extends JpaRepository<DailyPlanAccountEntity, Long> {

    @Query("""
                SELECT
                    dpa.dailyPlanAccountId AS dailyPlanAccountId,
                    dpa.dailyPlanId AS dailyPlanId,
                    a.accountId AS accountId,
                    CONCAT(a.firstName, ' ', a.lastName) AS fullName,
                    a.phone AS phone,
                    a.genderCode AS genderCode,
                    a.genderName AS genderName,
                    a.professionalTypeCode AS professionalTypeCode,
                    a.professionalTypeName AS professionalTypeName,
                    a.thumbnail AS thumbnail,
                    a.accountStatusCode AS accountStatusCode,
                    a.accountStatusName AS accountStatusName,
                    dpa.shiftCode AS shiftCode,
                    dpa.shiftName AS shiftName
                FROM DailyPlanAccountEntity dpa
                INNER JOIN AccountEntity a ON a.accountId = dpa.accountId
                WHERE dpa.dailyPlanId = :dailyPlanId
            """)
    List<DailyPlanAccountDAO> findAllByDailyPlanId(Long dailyPlanId);

    @Query("""
                SELECT
                    dpa.dailyPlanAccountId AS dailyPlanAccountId,
                    dpa.dailyPlanId AS dailyPlanId,
                    a.accountId AS accountId,
                    CONCAT(a.firstName, ' ', a.lastName) AS fullName,
                    a.phone AS phone,
                    a.genderCode AS genderCode,
                    a.genderName AS genderName,
                    a.professionalTypeCode AS professionalTypeCode,
                    a.professionalTypeName AS professionalTypeName,
                    a.thumbnail AS thumbnail,
                    a.accountStatusCode AS accountStatusCode,
                    a.accountStatusName AS accountStatusName
                FROM DailyPlanAccountEntity dpa
                INNER JOIN AccountEntity a ON a.accountId = dpa.accountId
                WHERE dpa.dailyPlanId IN :dailyPlanIds
            """)
    List<DailyPlanAccountDAO> findAllByDailyPlanIdIn(List<Long> dailyPlanIds);

    void deleteAllByDailyPlanId(Long dailyPlanId);
}

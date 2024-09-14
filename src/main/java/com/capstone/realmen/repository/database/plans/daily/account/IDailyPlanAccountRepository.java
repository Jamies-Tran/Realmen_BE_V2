package com.capstone.realmen.repository.database.plans.daily.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.data.dao.plans.daily.account.DailyPlanAccountDAO;
import com.capstone.realmen.service.plans.others.daily.plan.others.account.data.DailyPlanAccountSearchCriteria;

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
                    a.accountStatusName AS accountStatusName,
                    dpa.shiftCode AS shiftCode,
                    dpa.shiftName AS shiftName
                FROM DailyPlanAccountEntity dpa
                INNER JOIN AccountEntity a ON a.accountId = dpa.accountId
                WHERE dpa.dailyPlanId IN :dailyPlanIds
            """)
    List<DailyPlanAccountDAO> findAllByDailyPlanIdIn(List<Long> dailyPlanIds);

    void deleteAllByDailyPlanId(Long dailyPlanId);

    @Query("""
            SELECT
                    dpa.dailyPlanAccountId AS dailyPlanAccountId,
                    dpa.dailyPlanId AS dailyPlanId,
                    dp.date AS date,
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
                INNER JOIN DailyPlanEntity dp ON dp.dailyPlanId = dpa.dailyPlanId
                WHERE dpa.dailyPlanAccountId = :dailyPlanAccountId
            """)
    Optional<DailyPlanAccountDAO> findByDailyPlanAccountId(Long dailyPlanAccountId);

    @Query("""
            SELECT
                    dpa.dailyPlanAccountId AS dailyPlanAccountId,
                    dpa.dailyPlanId AS dailyPlanId,
                    dp.date AS date,
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
                INNER JOIN DailyPlanEntity dp ON dp.dailyPlanId = dpa.dailyPlanId
                WHERE (:#{#searchCriteria.hasDailyPlanIdEmpty()} = TRUE
                    OR dpa.dailyPlanId = :#{#searchCriteria.dailyPlanId})
                AND (:#{#searchCriteria.hasProfessionalTypeCodeEmpty()} = TRUE
                    OR a.professionalTypeCode IN :#{#searchCriteria.professionalTypeCodes()})
            """)
    Page<DailyPlanAccountDAO> findAll(DailyPlanAccountSearchCriteria searchCriteria, Pageable pageable);
}

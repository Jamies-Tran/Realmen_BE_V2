package com.capstone.realmen.repository.database.booking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.service.booking.data.BookingSearchCriteria;

@Repository
public interface IBookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query("""
            SELECT b
            FROM BookingEntity b
            LEFT JOIN DailyPlanEntity dp ON b.dailyPlanId = dp.dailyPlanId
            WHERE (:#{#searchCriteria.hasBranchIdEmpty()} = TRUE
                OR b.branchId = :#{#searchCriteria.branchId})
            AND (:#{#searchCriteria.hasDailyPlanIdEmpty()} = TRUE
                OR dp.dailyPlanId = :#{#searchCriteria.dailyPlanId()})
            AND (:#{#searchCriteria.hasStatusCodeEmpty()} = TRUE
                OR b.statusCode IN :#{#searchCriteria.statusCodes()})
            """)
    Page<BookingEntity> findAll(BookingSearchCriteria searchCriteria, Pageable pageable);
}

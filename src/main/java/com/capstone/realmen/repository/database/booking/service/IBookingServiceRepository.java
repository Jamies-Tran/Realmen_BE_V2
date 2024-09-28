package com.capstone.realmen.repository.database.booking.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.data.dao.booking.service.BookingServiceDAO;
import com.capstone.realmen.service.booking.other.data.BookingServiceCountRequire;
import com.capstone.realmen.service.booking.other.data.BookingServiceSearchCriteria;

@Repository
public interface IBookingServiceRepository extends JpaRepository<BookingServiceEntity, Long> {
    List<BookingServiceEntity> findByBookingId(Long bookingId);

    @Query("""
            SELECT COUNT(bs)
            FROM BookingServiceEntity bs
            INNER JOIN BookingEntity b ON bs.bookingId = b.bookingId
            INNER JOIN DailyPlanEntity dp ON b.dailyPlanId = dp.dailyPlanId
            WHERE bs.staffId = :#{#countRequire.staffId()}
                AND dp.date = :#{#countRequire.date()}
                AND (bs.beginAt BETWEEN :from AND :to
                    OR bs.finishAt BETWEEN :from AND :to)
                AND bs.statusCode IN :#{#countRequire.statusCodes()}
            """)
    Integer coungBooking(BookingServiceCountRequire countRequire, LocalTime from, LocalTime to);

    @Query("""
                SELECT
                    bs.bookingServiceId AS bookingServiceId,
                    dps.dailyPlanServiceId AS dailyPlanServiceId,
                    b.bookingId AS bookingId,
                    astaff.accountId AS staffId,
                    ss.shopServiceName AS shopServiceName,
                    bs.price AS price,
                    b.bookingCode AS bookingCode,
                    b.bookedAt AS bookedAt,
                    astaff.staffCode AS staffCode,
                    CONCAT(astaff.firstName, ' ', astaff.lastName) AS staffName,
                    astaff.phone AS staffPhone,
                    bs.beginAt AS beginAt,
                    bs.finishAt AS finishAt,
                    bs.actualBeginAt AS actualBeginAt,
                    bs.actualFinishedAt AS actualFinishedAt,
                    bs.statusCode AS statusCode,
                    bs.statusName AS statusName
                FROM BookingServiceEntity bs
                INNER JOIN BookingEntity b ON bs.bookingId = b.bookingId
                INNER JOIN AccountEntity astaff ON bs.staffId = astaff.accountId
                INNER JOIN DailyPlanServiceEntity dps ON dps.dailyPlanServiceId = bs.dailyPlanServiceId
                INNER JOIN BranchServiceEntity brs ON brs.branchServiceId = dps.branchServiceId
                INNER JOIN ShopServiceEntity ss ON brs.shopServiceId = ss.shopServiceId
                WHERE (:#{#searchCriteria.hasTimeRangeEmpty()} = TRUE
                    OR bs.createdAt BETWEEN :#{#searchCriteria.timeFrom()} AND :#{#searchCriteria.timeTo()})
                AND (:#{#searchCriteria.hasStatusCodesEmpty()} = TRUE
                    OR bs.statusCode IN :#{#searchCriteria.statusCodes()})
                AND (:#{#searchCriteria.hasStaffIdEmpty()} = TRUE
                    OR bs.staffId = :#{#searchCriteria.staffId()})
                AND (:#{#searchCriteria.hasBookingIdEmpty()} = TRUE
                    OR bs.bookingId = :#{#searchCriteria.bookingId()})
            """)
    Page<BookingServiceDAO> findAll(BookingServiceSearchCriteria searchCriteria, Pageable pageable);
}

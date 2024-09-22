package com.capstone.realmen.repository.database.booking.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capstone.realmen.service.booking.other.data.BookingServiceCountRequire;

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
}

package com.capstone.realmen.data.dto.booking;

import java.time.LocalDate;
import java.util.List;

import com.capstone.realmen.data.dto.booking.service.BookingService;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Booking(
        Long bookingId,
        Long accountId,
        Long branchId,
        Long dailyPlanId,
        LocalDate bookedAt,
        String bookingCode,
        String bookingMethodCode,
        String bookingMethodName,
        String statusCode,
        String statusName,
        Long totalPrice,
        List<BookingService> bookingServices) {

}

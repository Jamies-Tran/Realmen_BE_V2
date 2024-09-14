package com.capstone.realmen.service.booking.other.data;

import java.util.List;

import com.capstone.realmen.data.dto.booking.service.BookingService;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;

import lombok.Builder;

@Builder
public record BookingServiceCreateRequire( 
    Long bookingId, 
    List<BookingService> bookingServices,
    List<DailyPlanService> services) {
    public static BookingServiceCreateRequire of(Long bookingId, List<BookingService> bookingServices, List<DailyPlanService> services) {
        return BookingServiceCreateRequire.builder()
                .bookingId(bookingId)
                .bookingServices(bookingServices)
                .services(services)
                .build();
    }
}

package com.capstone.realmen.service.booking.other.data;

import java.util.List;
import com.capstone.realmen.data.dto.booking.service.BookingService;
import com.capstone.realmen.data.dto.branch.service.BranchService;
import com.capstone.realmen.data.dto.plans.daily.service.DailyPlanService;

import lombok.Builder;

@Builder
public record BookingServiceCreateRequire( 
    Long bookingId, 
    List<BookingService> bookingServices,
    List<DailyPlanService> dailyPlanServices,
    List<BranchService> branchServices) {

    public static BookingServiceCreateRequire createByDailyPlanService(Long bookingId, List<BookingService> bookingServices, List<DailyPlanService> services) {
        return BookingServiceCreateRequire.builder()
                .bookingId(bookingId)
                .bookingServices(bookingServices)
                .dailyPlanServices(services)
                .build();
    }

    public static BookingServiceCreateRequire createByBranchService(Long bookingId, List<BookingService> bookingServices, List<BranchService> services) {
        return BookingServiceCreateRequire.builder()
                .bookingId(bookingId)
                .bookingServices(bookingServices)
                .branchServices(services)
                .build();
    }

}

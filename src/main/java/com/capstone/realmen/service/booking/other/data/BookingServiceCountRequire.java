package com.capstone.realmen.service.booking.other.data;

import java.time.LocalDate;
import java.util.List;

import com.capstone.realmen.common.enums.EBookingStatus;
import com.capstone.realmen.data.dto.plans.daily.DailyPlanShiftHour;

import lombok.Builder;

@Builder
public record BookingServiceCountRequire(Long staffId, LocalDate date, List<DailyPlanShiftHour> shifts) {
    public static BookingServiceCountRequire of(Long staffId, LocalDate date, List<DailyPlanShiftHour> shifts) {
        return BookingServiceCountRequire.builder()
            .staffId(staffId)
            .date(date)
            .shifts(shifts)
            .build();
    }

    public List<String> statusCodes() {
        return List.of(EBookingStatus.AWAITING_PROCESS.getCode());
    }
}   

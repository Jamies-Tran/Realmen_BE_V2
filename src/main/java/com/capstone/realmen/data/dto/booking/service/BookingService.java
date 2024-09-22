package com.capstone.realmen.data.dto.booking.service;

import java.time.LocalTime;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record BookingService(
        Long bookingServiceId,
        Long branchServiceId,
        Long bookingId,
        Long serviceId,
        Long dailyPlanServiceId,
        Long staffId,
        Long price,
        String pickUpTypeCode,
        String pickUpTypeName,
        LocalTime beginAt,
        LocalTime finishAt,
        LocalTime actualBeginAt,
        LocalTime actualFinishedAt) {

}

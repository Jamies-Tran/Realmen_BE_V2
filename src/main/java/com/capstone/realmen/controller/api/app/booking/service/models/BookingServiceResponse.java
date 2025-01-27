package com.capstone.realmen.controller.api.app.booking.service.models;

import java.time.LocalTime;

import com.capstone.realmen.data.dto.booking.service.action.BookingServiceAction;

import lombok.Builder;

@Builder
public record BookingServiceResponse(
        Long bookingServiceId,
        Long branchServiceId,
        Long bookingId,
        Long dailyPlanServiceId,
        Long staffId,
        Long price,
        LocalTime beginAt,
        LocalTime finishAt,
        LocalTime actualBeginAt,
        LocalTime actualFinishedAt,
        String statusCode,
        String statusName,
        BookingServiceAction action) {

}

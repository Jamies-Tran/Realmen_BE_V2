package com.capstone.realmen.controller.api.app.booking.models;

import java.util.List;

import com.capstone.realmen.controller.api.app.booking.service.models.BookingServiceRequest;

import lombok.Builder;

@Builder
public record BookingRequest(
    Long branchId,
    Long dailyPlanId,
    List<BookingServiceRequest> bookingServices
) {
    
}

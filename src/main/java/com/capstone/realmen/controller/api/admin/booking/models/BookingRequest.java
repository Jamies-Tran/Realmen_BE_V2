package com.capstone.realmen.controller.api.admin.booking.models;

import java.util.List;

import lombok.Builder;

@Builder
public record BookingRequest(
        Long branchId,
        Long dailyPlanId,
        Customer customer,
        List<BookingServiceRequest> bookingServices) {

}

package com.capstone.realmen.controller.api.admin.booking.models;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;

@Builder
public record BookingRequest(
        Long branchId,
        LocalDate bookedAt,
        Customer customer,
        List<BookingServiceRequest> bookingServices) {

}

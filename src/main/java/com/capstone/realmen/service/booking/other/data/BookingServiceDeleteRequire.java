package com.capstone.realmen.service.booking.other.data;

import lombok.Builder;

@Builder
public record BookingServiceDeleteRequire(Long bookingServiceId, Long bookingId) {
    public static BookingServiceDeleteRequire of(Long bookingServiceId) {
        return BookingServiceDeleteRequire.builder()
                .bookingServiceId(bookingServiceId)
                .build();
    }

    public static BookingServiceDeleteRequire ofBookingId(Long bookingId) {
        return BookingServiceDeleteRequire.builder()
                .bookingId(bookingId)
                .build();
    }

}

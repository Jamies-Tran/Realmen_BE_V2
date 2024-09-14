package com.capstone.realmen.service.booking.data;

import lombok.Builder;

@Builder
public record BookingDeleteRequire(Long bookingId) {
    public static BookingDeleteRequire of(Long bookingId) {
        return BookingDeleteRequire.builder()
            .bookingId(bookingId)
            .build();
    }
}

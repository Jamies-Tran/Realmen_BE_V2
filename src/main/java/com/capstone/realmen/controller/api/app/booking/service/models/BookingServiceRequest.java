package com.capstone.realmen.controller.api.app.booking.service.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import lombok.Builder;

@Builder
public record BookingServiceRequest(
    Long serviceId,
    Long staffId,
    Long price,
    LocalDateTime beginAtReq,
    LocalTime beginAt
) {
    public LocalTime beginAt() {
        return Objects.requireNonNullElse(beginAt, beginAtReq.toLocalTime());
    }
}

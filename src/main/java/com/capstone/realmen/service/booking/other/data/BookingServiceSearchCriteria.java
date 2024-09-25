package com.capstone.realmen.service.booking.other.data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.capstone.realmen.common.util.DateTimeHandler;

import lombok.Builder;

@Builder
public record BookingServiceSearchCriteria(
        List<LocalDateTime> timeRange,
        List<String> statusCodes,
        Long staffId,
        Long bookingId) {

    public static BookingServiceSearchCriteria of(List<LocalDateTime> timeRange, List<String> statusCodes, Long staffId,
            Long bookingId) {
        return BookingServiceSearchCriteria.builder()
                .timeRange(DateTimeHandler.validateTimeRange(timeRange))
                .statusCodes(statusCodes)
                .staffId(staffId)
                .bookingId(bookingId)
                .build();
    }

    public Boolean hasTimeRangeEmpty() {
        return Objects.isNull(timeRange) || timeRange.isEmpty();
    }

    public Boolean hasStatusCodesEmpty() {
        return Objects.isNull(statusCodes) || statusCodes.isEmpty();
    }

    public Boolean hasStaffIdEmpty() {
        return Objects.isNull(staffId);
    }

    public Boolean hasBookingIdEmpty() {
        return Objects.isNull(bookingId);
    }

    public LocalDateTime timeFrom() {
        if (hasTimeRangeEmpty()) {
            return null;
        }
        return timeRange.get(0);
    }

    public LocalDateTime timeTo() {
        if (hasTimeRangeEmpty()) {
            return null;
        }
        return timeRange.get(1);
    }
}

package com.capstone.realmen.service.booking.other.helpers;

import java.time.LocalTime;

import com.capstone.realmen.common.enums.EDurationUnit;

public class BookingServiceHelper {
    protected LocalTime getEstimateFinishAt(LocalTime beginAt, Integer duration, String durationUnitCode) {
        switch(EDurationUnit.findByCode(durationUnitCode)) {
            case HOUR:
                return beginAt.plusHours(duration);
            case MINUTE:
                return beginAt.plusMinutes(duration);
            default:
                return beginAt;
        }
    }
}

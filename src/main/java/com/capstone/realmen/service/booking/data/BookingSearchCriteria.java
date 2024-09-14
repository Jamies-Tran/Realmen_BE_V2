package com.capstone.realmen.service.booking.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import lombok.Builder;

@Builder
public record BookingSearchCriteria(
    Long branchId,
    Long dailyPlanId,
    List<String> statusCodes,
    LocalDate createdAt
) {
    public static BookingSearchCriteria of(Long branchId, List<String> statusCodes, Long dailyPlanId) {
        return BookingSearchCriteria.builder()
            .branchId(branchId)
            .statusCodes(statusCodes)
            .dailyPlanId(dailyPlanId)
            .build();
    }

    public Boolean hasBranchIdEmpty() {
        return Objects.isNull(branchId);
    }

    public Boolean hasStatusCodeEmpty() {
        return Objects.isNull(statusCodes) || statusCodes.isEmpty();
    }

    public Boolean hasCreatedAtEmpty() {
        return Objects.isNull(createdAt);
    }

    public Boolean hasDailyPlanIdEmpty() {
        return Objects.isNull(dailyPlanId);
    }
}

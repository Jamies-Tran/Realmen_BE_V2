package com.capstone.realmen.service.booking.data;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import lombok.Builder;

@Builder
public record BookingSearchCriteria(
    Long accountId,
    Long branchId,
    Long dailyPlanId,
    List<String> statusCodes,
    LocalDate bookedAt,
    LocalDate createdAt
) {
    public static BookingSearchCriteria of(Long accountId, Long branchId, List<String> statusCodes, Long dailyPlanId) {
        return BookingSearchCriteria.builder()
            .accountId(accountId)
            .branchId(branchId)
            .statusCodes(statusCodes)
            .dailyPlanId(dailyPlanId)
            .build();
    }

    public static BookingSearchCriteria of(Long accountId, Long branchId, List<String> statusCodes, LocalDate bookedAt) {
        return BookingSearchCriteria.builder()
            .accountId(accountId)
            .branchId(branchId)
            .statusCodes(statusCodes)
            .bookedAt(bookedAt)
            .build();
    }

    public Boolean hasAccountIdEmpty() {
        return Objects.isNull(accountId);
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

    public Boolean hasBookedAtEmpty() {
        return Objects.isNull(bookedAt);
    }
}

package com.capstone.realmen.service.branch.data;

import lombok.Builder;

@Builder
public record BranchServiceRequire(
        Long shopServiceId,
        Integer estimateDuration,
        String durationUnitCode,
        String durationUnitName,
        Long price) {

}

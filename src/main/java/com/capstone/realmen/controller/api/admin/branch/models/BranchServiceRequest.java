package com.capstone.realmen.controller.api.admin.branch.models;

import lombok.Builder;

@Builder
public record BranchServiceRequest(
        Long shopServiceId,
        Integer estimateDuration,
        String durationUnitCode,
        String durationUnitName,
        Long price) {

}

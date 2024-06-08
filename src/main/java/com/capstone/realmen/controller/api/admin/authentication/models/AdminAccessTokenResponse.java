package com.capstone.realmen.controller.api.admin.authentication.models;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AdminAccessTokenResponse(
    Long accountId,
        Long branchId,
        String staffCode,
        String lastName,
        String firstName,
        String accessToken,
        LocalDateTime issueAt,
        String roleCode,
        String roleName
) {
    
}

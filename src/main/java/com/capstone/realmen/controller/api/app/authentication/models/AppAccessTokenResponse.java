package com.capstone.realmen.controller.api.app.authentication.models;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AppAccessTokenResponse(
        Long accountId,
        String lastName,
        String firstName,
        String accessToken,
        LocalDateTime issueAt,
        String roleCode,
        String roleName) {

}

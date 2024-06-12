package com.capstone.realmen.controller.api.admin.account.models;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountCreatedResponse(
    Long accountId,
    Boolean isActivated
) {
    
}

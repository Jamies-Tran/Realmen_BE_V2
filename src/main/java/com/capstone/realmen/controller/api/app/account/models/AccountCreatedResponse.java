package com.capstone.realmen.controller.api.app.account.models;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountCreatedResponse(
        Long accountId,
        Boolean isActivated) {

}

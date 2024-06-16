package com.capstone.realmen.service.authentication.helper.cache.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record OtpSearchByField(
        Long accountId) {
    public static OtpSearchByField of(Long accountId) {
        return init().withAccountId(accountId);
    }

    private static OtpSearchByField init() {
        return OtpSearchByField.builder().build();
    }
}

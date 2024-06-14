package com.capstone.realmen.service.cache.token.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record SearchByField(
        Long accountId) {
    public static SearchByField of(Long accountId) {
        return init().withAccountId(accountId);
    }

    private static SearchByField init() {
        return SearchByField.builder().build();
    }
}

package com.capstone.realmen.service.account.data;

import java.util.List;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountSearchByField(
        Long accountId,
        String search,
        List<Long> accountIds) {

    public static AccountSearchByField of(String search) {
        return init().withSearch(search);
    }

    public static AccountSearchByField of(Long accountId) {
        return init().withAccountId(accountId);
    }

    private static AccountSearchByField init() {
        return AccountSearchByField.builder().build();
    }
}

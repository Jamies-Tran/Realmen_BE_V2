package com.capstone.realmen.service.account.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AccountSearchByField(String search) {

    public static AccountSearchByField of(String search) {
        return init().withSearch(search);
    }

    private static AccountSearchByField init() {
        return AccountSearchByField.builder().build();
    }
}

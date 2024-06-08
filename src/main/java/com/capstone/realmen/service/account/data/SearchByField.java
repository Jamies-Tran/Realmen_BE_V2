package com.capstone.realmen.service.account.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record SearchByField(String search) {

    public static SearchByField of(String search) {
        return init().withSearch(search);
    }

    private static SearchByField init() {
        return SearchByField.builder().build();
    }
}

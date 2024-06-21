package com.capstone.realmen.service.collection.assignment.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ServiceAssignmentSearchByField(String name) {

    public static ServiceAssignmentSearchByField of(String name) {
        return ServiceAssignmentSearchByField.builder()
                .name(name.toLowerCase())
                .build();
    }
}

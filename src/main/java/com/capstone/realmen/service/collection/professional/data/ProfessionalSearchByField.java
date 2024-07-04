package com.capstone.realmen.service.collection.professional.data;

import lombok.Builder;

@Builder
public record ProfessionalSearchByField(
    String name
) {
    public String name() {
        return name.toLowerCase();
    }
}

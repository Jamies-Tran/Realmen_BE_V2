package com.capstone.realmen.controller.api.collection.assignment.models;

import lombok.Builder;

@Builder
public record ServiceAssignmentResponse(
    String code,
    String name
) {
    
}

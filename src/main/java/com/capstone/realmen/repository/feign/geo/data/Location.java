package com.capstone.realmen.repository.feign.geo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record Location(
    @JsonProperty("lat") Double latitude,
    @JsonProperty("long") Double longitude
) {
    
}

package com.capstone.realmen.repository.feign.geo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AddressComponent(
    @JsonProperty("long_name") String addressComponent
) {
    
}

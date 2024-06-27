package com.capstone.realmen.repository.feign.geo.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record GoongAddress(
    @JsonProperty("address_components") List<AddressComponent> addressComponents,
    Geometry geometry
) {
    
}

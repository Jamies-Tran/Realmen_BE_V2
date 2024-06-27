package com.capstone.realmen.repository.feign.geo.data;

import lombok.Builder;

@Builder
public record Geometry(
    Location location
) {
    
}

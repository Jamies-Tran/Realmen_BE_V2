package com.capstone.realmen.data.dto.branch.distance;

import lombok.Builder;

@Builder
public record LatLng(
        Double latitude,
        Double longitude) {

}

package com.capstone.realmen.data.dto.branch.distance;

import lombok.Builder;

@Builder
public record DistanceInKm(
        Double distance) {
            
    public static DistanceInKm ofDefault() {
        return DistanceInKm.builder().build();
    }

    public static DistanceInKm of(LatLng from, LatLng to) {
        Double distance = Math.acos(Math.sin(from.latitude()) * Math.sin(to.latitude())
                + Math.cos(from.latitude()) * Math.cos(to.latitude())
                        * Math.cos(to.latitude() - from.longitude()))
                * 6371;
        return DistanceInKm.builder()
                .distance(distance)
                .build();
    }
}

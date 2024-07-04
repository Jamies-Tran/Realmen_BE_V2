package com.capstone.realmen.data.dto.branch.distance;

import lombok.Builder;

@Builder
public record DistanceInKm(
        Double distance) {
            
    public static DistanceInKm ofDefault() {
        return DistanceInKm.builder().build();
    }

    public static DistanceInKm of(LatLng from, LatLng to) {
        double dlat = to.rLatitude() - from.rLatitude();
        double dlong = to.rLongitude() - from.rLongitude();

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(from.rLatitude()) * Math.cos(to.rLatitude()) 
                * Math.pow(Math.sin(dlong / 2), 2);

        double c = Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = c * 6371.0;

        return DistanceInKm.builder()
                .distance(distance)
                .build();
    }
}

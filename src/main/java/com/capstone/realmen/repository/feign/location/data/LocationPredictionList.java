package com.capstone.realmen.repository.feign.location.data;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record LocationPredictionList(
                @JsonProperty("predictions") List<LocationPrediction> locationList) {
        public LocationPredictionList {
                if (Objects.isNull(locationList)) {
                        locationList = List.of();
                }
        }

}

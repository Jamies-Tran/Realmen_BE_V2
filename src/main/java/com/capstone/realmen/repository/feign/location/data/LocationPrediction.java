package com.capstone.realmen.repository.feign.location.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record LocationPrediction(
        @JsonProperty("description") String location) {

}

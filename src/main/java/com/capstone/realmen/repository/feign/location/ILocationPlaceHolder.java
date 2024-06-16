package com.capstone.realmen.repository.feign.location;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.repository.feign.location.data.LocationPredictionList;

@FeignClient(name = "location", url = "${goong.place.autocomplete}")
@Import(value = FeignClientConfiguration.class)
public interface ILocationPlaceHolder {
    @GetMapping
    LocationPredictionList getAddress(
            @RequestParam(value = "api_key") String apiKey,
            @RequestParam(value = "input") String search);
}

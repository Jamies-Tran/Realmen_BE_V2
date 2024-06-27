package com.capstone.realmen.repository.feign.geo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.realmen.repository.feign.geo.data.GoongAddressResponse;

@FeignClient(name = "Geometry", url = "${goong.geometry}")
@Import(value = FeignClientConfiguration.class)
public interface IGeometryPlaceHolder {
    @GetMapping
    GoongAddressResponse getGoongAddress(
        @RequestParam(value = "api_key") String apiKey,
        @RequestParam(value = "address", defaultValue = "") String address
    );
}

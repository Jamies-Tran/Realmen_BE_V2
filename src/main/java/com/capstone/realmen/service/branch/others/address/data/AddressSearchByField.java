package com.capstone.realmen.service.branch.others.address.data;

import org.springframework.beans.factory.annotation.Value;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AddressSearchByField(
    @Value("#{goong.api.key}") String apiKey,
    String address) {

}

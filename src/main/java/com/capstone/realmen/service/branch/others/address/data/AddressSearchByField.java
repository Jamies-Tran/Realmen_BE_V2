package com.capstone.realmen.service.branch.others.address.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AddressSearchByField(
    String apiKey,
    String address) {

}

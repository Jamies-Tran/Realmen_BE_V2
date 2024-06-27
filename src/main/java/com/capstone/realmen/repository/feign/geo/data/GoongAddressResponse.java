package com.capstone.realmen.repository.feign.geo.data;

import java.util.List;

public record GoongAddressResponse(
    List<GoongAddress> results
) {
    
}

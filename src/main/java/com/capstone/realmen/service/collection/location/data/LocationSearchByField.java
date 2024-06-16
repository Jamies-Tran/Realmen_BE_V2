package com.capstone.realmen.service.collection.location.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record LocationSearchByField(
    String apiKey,
    String search
) {
    
}

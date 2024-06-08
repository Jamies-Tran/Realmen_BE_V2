package com.capstone.realmen.data.dto.account.professional;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record Professional(
    String code,
    String name
) {
    
}

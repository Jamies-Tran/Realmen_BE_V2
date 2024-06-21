package com.capstone.realmen.data.dto.shop.category.assignment;

import com.capstone.realmen.data.dto.account.professional.Professional;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ServiceAssignment(
    String code,
    String name,
    Professional professional
) {
    
}

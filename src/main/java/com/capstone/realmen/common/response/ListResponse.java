package com.capstone.realmen.common.response;

import java.util.List;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record ListResponse<T>(List<T> values) {
    
}

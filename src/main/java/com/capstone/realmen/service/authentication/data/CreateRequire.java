package com.capstone.realmen.service.authentication.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record CreateRequire(
                String identify,
                String password) {
}

package com.capstone.realmen.service.authentication.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record CreateRequire(
        String staffCode,
        String phone,
        String password) {
}

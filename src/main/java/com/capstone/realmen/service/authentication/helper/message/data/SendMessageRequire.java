package com.capstone.realmen.service.authentication.helper.message.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record SendMessageRequire(
        String phone,
        String message) {

    public String phone() {
        return "+84".concat(phone.substring(0));
    }
}

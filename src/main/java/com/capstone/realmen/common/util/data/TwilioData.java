package com.capstone.realmen.common.util.data;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record TwilioData(
    String accountSID,
    String authToken,
    String twilioPhoneNumber
) {
    private static TwilioData ofDefault() {
        return init()
            .withAccountSID("")
            .withAuthToken("")
            .withTwilioPhoneNumber("");
    }

    private static TwilioData init() {
        return TwilioData.builder().build();
    }
}

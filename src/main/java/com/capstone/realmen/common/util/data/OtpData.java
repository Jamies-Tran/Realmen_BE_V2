package com.capstone.realmen.common.util.data;

import java.util.Random;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record OtpData(String otp) {
    public static OtpData OfDefault() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            otp.append(new Random().nextInt(9));
        }
        return OtpData.builder().otp(otp.toString()).build();
    }
}

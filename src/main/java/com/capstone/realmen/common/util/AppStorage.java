package com.capstone.realmen.common.util;

import com.capstone.realmen.common.util.data.OtpData;
import com.capstone.realmen.common.util.data.RandomCodeData;
import com.capstone.realmen.common.util.data.TwilioData;

public class AppStorage {
    public static TwilioData twilioData() {
        return TwilioData.ofDefault();
    }

    public static OtpData otpData() {
        return OtpData.OfDefault();
    }

    public static RandomCodeData generaterandomCode() {
        return RandomCodeData.generate();
    }
}

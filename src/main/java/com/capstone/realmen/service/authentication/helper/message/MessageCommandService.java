package com.capstone.realmen.service.authentication.helper.message;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.util.AppStorage;
import com.capstone.realmen.service.authentication.helper.message.data.SendMessageRequire;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class MessageCommandService {
    public void sendOtp(SendMessageRequire sendMessageRequire) {
        Twilio.init(
                AppStorage.twilioData().accountSID(),
                AppStorage.twilioData().authToken());
        Message.creator(new PhoneNumber(sendMessageRequire.phone()),
                new PhoneNumber(AppStorage.twilioData().twilioPhoneNumber()),
                sendMessageRequire.message()).create();
    }
}

package com.twiliosms.api.service;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendSms(String toPhoneNumber, String messageBody) {
        try {
            Twilio.init(accountSid, authToken);
            Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(twilioPhoneNumber), messageBody).create();
            System.out.println("SMS sent successfully to: " + message.getTo());
        } catch (ApiException e) {
            System.err.println("Failed to send SMS: " + e.getMessage());
            // Handle the exception or log the error as per your application's requirements.
        }
    }
}

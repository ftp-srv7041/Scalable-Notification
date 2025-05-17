package com.souravkumar.SMSConsumer.service;

import com.souravkumar.SMSConsumer.models.SendSmsResponse;
import com.souravkumar.SMSConsumer.models.SmsRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService {
    public final String ACCOUNT_SID = "your_SID_goes_here";
    public final String AUTH_TOKEN = "your_AUTH_TOKEN_goes_here";

    public SendSmsResponse sendSms(SmsRequest smsRequest){

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try{
            Message message = Message
                    .creator(
                            new PhoneNumber("+91" + smsRequest.getMobileNumber()),
                            new PhoneNumber("your_TWILIO_phone_number_goes_here"),
                            smsRequest.getMessage()
                    )
                    .create();

            log.info("Sms Request (Notification Id: {}). Response from Twilio: \n Status: {}, Body: {}, Twilio_Message: {}",smsRequest.getNotificationId(),message.getStatus(),message.getBody(), message.toString());
            return new SendSmsResponse(200,"Sid: "+message.getSid()+" Body: "+message.getBody());
        }catch (Exception exception){
            log.error("Something went wrong with Twilio. Exception: {}",exception.toString());
            return new SendSmsResponse(500, "Exception occurred in Sms Service-Twilio");
        }
    }
}

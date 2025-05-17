package com.souravkumar.SMSConsumer.service;

import com.souravkumar.SMSConsumer.models.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public class FailedNotificationsHandlerService {
    public void handleFailedRequest(SmsRequest smsRequest){
        //implement retry strategy or logging for failed notifications
    }
}

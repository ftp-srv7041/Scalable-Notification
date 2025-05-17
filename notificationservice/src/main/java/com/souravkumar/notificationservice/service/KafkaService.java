package com.souravkumar.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.souravkumar.notificationservice.models.NotificationRequest;
import com.souravkumar.notificationservice.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {

    KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationRequest notificationRequest){

        try {
            String notification = prepareMessage(notificationRequest);
            if (notificationRequest.getNotificationPriority() == 1) {
                this.kafkaTemplate.send(Constants.TOPIC_PRIORITY_1, notification);
            } else if (notificationRequest.getNotificationPriority() == 2) {
                this.kafkaTemplate.send(Constants.TOPIC_PRIORITY_2, notification);
            } else {
                this.kafkaTemplate.send(Constants.TOPIC_PRIORITY_3, notification);
            }
            log.info("Notification Successfully forwarded to Kafka with priority: "+notificationRequest.getNotificationPriority());
        } catch (Exception e){
            throw new KafkaException("Failed to send notification", e);
        }
    }

    private String prepareMessage(NotificationRequest notificationRequest) throws JsonProcessingException {
        //Need to send NotificationRequest as String data to kafka
        //or otherwise use protobuf, etc to provide serializer to KAFKA
        //used when we want strict Data Types

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(notificationRequest);
    }
}

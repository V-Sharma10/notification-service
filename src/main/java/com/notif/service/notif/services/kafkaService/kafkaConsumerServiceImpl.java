package com.notif.service.notif.services.kafkaService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "sms_request_new", groupId = "msg_grp")
public class kafkaConsumerServiceImpl implements kafkaConsumerService{
    @Override
    public void listener(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}

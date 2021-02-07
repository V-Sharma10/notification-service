package com.notif.service.notif.services.kafkaService;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumerServiceImpl implements kafkaConsumerService{


    @KafkaListener(topics = "${kafka.topic}",groupId = "${kafka.groupid}")
    @Override
    public void listener(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

}



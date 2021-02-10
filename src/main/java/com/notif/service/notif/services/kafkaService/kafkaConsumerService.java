package com.notif.service.notif.services.kafkaService;

import org.springframework.stereotype.Service;

@Service
public interface kafkaConsumerService {
    public void listener(String message);
}

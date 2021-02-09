package com.notif.service.notif.services.kafkaService;

import org.springframework.stereotype.Service;

@Service
public interface kafkaProducerService {
   void sendMessage(String msg) ;
}

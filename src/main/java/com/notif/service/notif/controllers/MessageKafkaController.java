package com.notif.service.notif.controllers;

import com.notif.service.notif.services.kafkaService.KafkaProducerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/kafka")
public class MessageKafkaController {
    @Autowired
    KafkaProducerServiceImpl kafkaProducerService;

    @GetMapping("/{msg}")
    public String sendMsgKafka(@PathVariable String msg){
        kafkaProducerService.sendMessage(msg);
        return "Successfully sent";
    }
}

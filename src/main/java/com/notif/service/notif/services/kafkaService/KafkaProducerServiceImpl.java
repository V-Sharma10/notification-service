package com.notif.service.notif.services.kafkaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducerServiceImpl implements kafkaProducerService {
    Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    @Value("${kafka.topic}")
    private String TOPIC;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        logger.info("sendMessage, id={}",msg);
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(TOPIC, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sent message=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
                 }
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message=["
                        + msg + "] due to : " + ex.getMessage());

            }
        });
    }
}

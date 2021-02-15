package com.notif.service.notif.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    Logger logger = LoggerFactory.getLogger(KafkaTopicConfig.class);
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
    @Value("${kafka.topic}")
    private String TOPIC;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        logger.info("Kafka Admin connected.");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        logger.info("Topic initialized in kafka.");
        return new NewTopic(TOPIC, 1, (short) 1);
    }
}
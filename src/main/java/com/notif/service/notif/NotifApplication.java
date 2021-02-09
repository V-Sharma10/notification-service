package com.notif.service.notif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableCaching
@EntityScan("com.notif.service.notif.models")
@EnableKafka
public class NotifApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifApplication.class, args);
    }

}

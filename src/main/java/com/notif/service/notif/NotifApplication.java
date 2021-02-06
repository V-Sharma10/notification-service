package com.notif.service.notif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.notif.service.notif.models")
public class NotifApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifApplication.class, args);
    }

}

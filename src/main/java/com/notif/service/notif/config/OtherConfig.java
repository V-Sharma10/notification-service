package com.notif.service.notif.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OtherConfig {
    Logger logger = LoggerFactory.getLogger(OtherConfig.class);
    @Value("${imiconnect_url}")
    private String url;
    @Value("${imiconnect_api_key}")
    private String key;

    @Bean
    public RestTemplate restTemplate(){

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(url)
                .defaultHeader("key",key)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
        logger.info("Rest template for 3rd party API configured");
        return restTemplate;
    }


}

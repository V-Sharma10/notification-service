package com.notif.service.notif.utils;

import com.notif.service.notif.models.request.MessageRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class IMIMessagingConnect {
    @Value("${imiconnect_url}")
    private String url;
//    @Value("${imiconnect_api_key}")
    private String key="78df18c7-ff74-42d4-838a-77ba5a466443";
//    @Autowired
//    private static RestTemplate restTemplate;

    public MessageRequestModel thirdPartyCall(MessageRequestModel msg) {
        RestTemplate restTemplate = new RestTemplateBuilder().rootUri(url)
                .defaultHeader("key",key)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).build();
        HttpEntity<MessageRequestModel> entity = new HttpEntity<>(msg);
        MessageRequestModel responseEntity = restTemplate.postForObject(url, entity, MessageRequestModel.class);

        return responseEntity;
    }
}


//@SpringRetry
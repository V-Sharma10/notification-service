package com.notif.service.notif.utils;

import com.notif.service.notif.models.request.imiconnect.ExternalSmsRequest;
import com.notif.service.notif.models.response.ExternalSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IMIMessagingConnect {
    @Value("${imiconnect_url}")
    private String url;
    @Value("${imiconnect_api_key}")
    private String key;
    @Autowired
    MessageToSendBuilder sendBuilder;

    public ExternalSmsResponse thirdPartyCall(String id, String phoneNumber, String msg) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(url)
                .defaultHeader("key",key)
                .defaultHeader("Content-Type",MediaType.APPLICATION_JSON_VALUE)
                .build();
            ExternalSmsRequest smsRequest = sendBuilder.buildMsgToSend(id,phoneNumber,msg);
            System.out.println(smsRequest);
//        ExternalSmsResponse response = restTemplate.postForObject(url,smsRequest,ExternalSmsResponse.class);
//        System.out.println(response);
    return null;
    }
}


//@SpringRetry
package com.notif.service.notif.utils.externalSmsApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notif.service.notif.models.request.imiconnect.ExternalSmsRequest;
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
//    @Value("${imiconnect_api_key}")
    private String key="";
    @Autowired
    MessageToSendBuilder sendBuilder;

    public String thirdPartyCall(String id, String phoneNumber, String msg) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(url)
                .defaultHeader("key",key)
                .defaultHeader("Content-Type",MediaType.APPLICATION_JSON_VALUE)
                .build();
        ExternalSmsRequest smsRequest = sendBuilder.buildMsgToSend(id,phoneNumber,msg);

        String response = restTemplate.postForObject(url,smsRequest,String.class);
        System.out.println(response);

        ObjectMapper mapper = new ObjectMapper();
    return null;
    }
}


//@SpringRetry
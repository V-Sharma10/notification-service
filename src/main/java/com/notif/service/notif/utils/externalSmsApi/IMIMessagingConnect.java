package com.notif.service.notif.utils.externalSmsApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notif.service.notif.models.request.imiconnect.ExternalSmsRequest;
import com.notif.service.notif.models.response.Response;
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

    public String thirdPartyCall(String id, String phoneNumber, String msg) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(url)
                .defaultHeader("key",key)
                .defaultHeader("Content-Type",MediaType.APPLICATION_JSON_VALUE)
                .build();
        ExternalSmsRequest smsRequest = sendBuilder.buildMsgToSend(id,phoneNumber,msg);
        try{
            String response = restTemplate.postForObject(url,smsRequest,String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            JsonNode responseNode = rootNode.path("response");
            JsonNode transid = rootNode.at("/response/transid");

            if(transid.toString().length()>0){
                return mapper.treeToValue(responseNode,Response.class).toString();
            }else{
                return responseNode.get(0).toString();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

    return "Failed to send the message: third party error";
    }
}


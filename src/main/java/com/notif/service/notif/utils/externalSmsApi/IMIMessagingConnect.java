package com.notif.service.notif.utils.externalSmsApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notif.service.notif.models.request.imiconnect.ExternalSmsRequest;
import com.notif.service.notif.models.response.Response;
import com.notif.service.notif.utils.enums.FailureEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IMIMessagingConnect {
    @Autowired
    MessageToSendBuilder sendBuilder;

    @Value("${imiconnect_url}")
    private String url;

    @Autowired
    public RestTemplate restTemplate;

    public String thirdPartyCall(String id, String phoneNumber, String msg) {

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

    return FailureEnums.THIRD_API_FAIL.getMessage();
    }
}


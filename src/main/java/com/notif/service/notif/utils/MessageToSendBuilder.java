package com.notif.service.notif.utils;

import com.notif.service.notif.models.request.imiconnect.Channels;
import com.notif.service.notif.models.request.imiconnect.Destination;
import com.notif.service.notif.models.request.imiconnect.ExternalSmsRequest;
import com.notif.service.notif.models.request.imiconnect.Text;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageToSendBuilder {
    public ExternalSmsRequest buildMsgToSend(String id,String phoneNumber,String msg){
        Text text = new Text(msg);
        Channels channels = Channels.builder().sms(text).build();

        List<String> msisdnList = new ArrayList<>();
        msisdnList.add(phoneNumber);

        List<Destination> destinationList = new ArrayList<>();
        Destination destination = Destination.builder().correlationId(id).msisdn(msisdnList).build();
        destinationList.add(destination);

        ExternalSmsRequest smsRequest = ExternalSmsRequest.builder()
                .deliveryChannel("sms")
                .channels(channels).destination(destinationList).build();
        return smsRequest ;
    }
}

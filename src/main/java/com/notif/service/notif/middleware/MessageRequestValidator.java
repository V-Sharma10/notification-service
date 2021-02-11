package com.notif.service.notif.middleware;

import com.notif.service.notif.models.request.MessageRequestModel;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MessageRequestValidator {
    MessageRequestModel requestModel;

    public MessageRequestValidator() {
    }

    public MessageRequestValidator(MessageRequestModel requestModel) {
        this.requestModel = requestModel;
    }
    public boolean phoneValidator(String s){
        Pattern p = Pattern.compile("^[6-9]\\d{9}$");

        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    public boolean messageValidator(String s){
        if(s.length()>5){
            return true;
        }
        return false;

    }
    public boolean main(MessageRequestModel message){
     return phoneValidator(message.getPhoneNumber()) && messageValidator(message.getMessage());
    }
}


package com.notif.service.notif.validators;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.utils.enums.ErrorCodes;
import com.notif.service.notif.utils.enums.ValidatorEnums;
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
        if(s.length()>5){return true; }
        return false;
    }

    public boolean main(MessageRequestModel message) throws InvalidRequestException {
        if(!phoneValidator(message.getPhoneNumber())){
            throw new InvalidRequestException(ValidatorEnums.PHONE_INVALID.getMessage(), ErrorCodes.BAD_REQUEST_ERROR);
        }else if(!messageValidator(message.getMessage())){
            throw new InvalidRequestException(ValidatorEnums.MESSAGE_INVALID.getMessage(), ErrorCodes.BAD_REQUEST_ERROR);
        }
     return  true;
    }
}


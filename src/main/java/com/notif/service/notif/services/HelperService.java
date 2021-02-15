package com.notif.service.notif.services;

import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.utils.enums.ErrorCodes;
import com.notif.service.notif.utils.enums.FailureEnums;
import com.notif.service.notif.utils.enums.StatusEnums;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HelperService {
    public MessageDtoModel sendingFailed(MessageDtoModel msgDtoConsumer){
        msgDtoConsumer.setUpdatedAt(new Date());
        msgDtoConsumer.setFailureCode(ErrorCodes.BAD_REQUEST_ERROR.getCode());
        msgDtoConsumer.setFailureComments(FailureEnums.BLACKLIST.getMessage());
        msgDtoConsumer.setStatus(StatusEnums.FAILED.getCode());

        return msgDtoConsumer;
    }

    public MessageDtoModel sendingSuccess(MessageDtoModel msgDtoConsumer,String thirdPartyResponse){
        msgDtoConsumer.setUpdatedAt(new Date());
        msgDtoConsumer.setThirdPartyResponse(thirdPartyResponse);
        msgDtoConsumer.setStatus(StatusEnums.SUCCESS.getCode());
        return msgDtoConsumer;
    }
}

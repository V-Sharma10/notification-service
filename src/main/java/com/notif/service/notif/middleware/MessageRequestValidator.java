package com.notif.service.notif.middleware;

import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.utils.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageRequestValidator {
    @Autowired
    Validators validators;
    MessageRequestModel requestModel;

    public MessageRequestValidator() {
    }

    public MessageRequestValidator(MessageRequestModel requestModel) {
        this.requestModel = requestModel;
    }


}

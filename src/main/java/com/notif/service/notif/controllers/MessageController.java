package com.notif.service.notif.controllers;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageModel;
import com.notif.service.notif.utils.ErrorCodes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/sms")
public class MessageController {


    @PostMapping
    public void sendMessage(MessageModel message){
        try{

        }
        catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage(), ErrorCodes.NOT_FOUND_ERROR);
        }
        catch (InvalidRequestException ex) {
            throw new InvalidRequestException(ex.getMessage(), ErrorCodes.BAD_REQUEST_ERROR);
        }
        catch (ServiceUnavailableException ex) {
            throw new ServiceUnavailableException(ex.getMessage(), ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }

    }
}


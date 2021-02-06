package com.notif.service.notif.controllers;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.models.response.Success;
import com.notif.service.notif.services.MessageService;
import com.notif.service.notif.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Validated
@RestController
@RequestMapping("v1/sms")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("send")
    public ResponseEntity sendMessage(@RequestBody @Valid MessageRequestModel message){
        try{
            String id = messageService.sendMsg(message);
            Success returnValue = new Success(id,"Successfully Sent.");
            return new ResponseEntity(returnValue, HttpStatus.OK);
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


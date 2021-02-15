package com.notif.service.notif.controllers;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.models.response.Success;
import com.notif.service.notif.services.MessageService;
import com.notif.service.notif.utils.enums.ErrorCodes;
import com.notif.service.notif.utils.enums.FailureEnums;
import com.notif.service.notif.utils.enums.SuccessEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;


@Validated
@RestController
@RequestMapping("v1/sms")
public class MessageController {

        Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    MessageService messageService;

    @GetMapping("/{id}")
    public Optional<MessageDtoModel> getById(@PathVariable String id){
        logger.info("getById where id = "+id);
        try{
            if(!messageService.getDetailsById(id).isPresent()){
                throw new NotFoundException(FailureEnums.CANT_FIND.getMessage(), ErrorCodes.NOT_FOUND_ERROR);
            }
            return messageService.getDetailsById(id);
        }
        catch (Exception ex){
            throw new ServiceUnavailableException(ex.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }
    }

    @PostMapping("send")
    public ResponseEntity sendMessage(@RequestBody @Valid MessageRequestModel message){
        logger.info("sendMessage method called");
        try{
            String id = messageService.sendMsg(message);
            Success returnValue = new Success(id, SuccessEnums.SUBMISSION_SUCCESS.getMessage());
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
        catch (InvocationTargetException ex){ }
        catch (IllegalAccessException ex){ }
    return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
    }
}


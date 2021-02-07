package com.notif.service.notif.controllers;

import com.notif.service.notif.exception.InvalidRequestException;
import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageDtoModel;
import com.notif.service.notif.models.request.MessageRequestModel;
import com.notif.service.notif.models.response.Success;
import com.notif.service.notif.services.MessageService;
import com.notif.service.notif.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Validated
@RestController
@RequestMapping("v1/sms")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/{id}")
    public Optional<MessageDtoModel> getById(@PathVariable String id){
        try{
            if(!messageService.getDetailsById(id).isPresent()){
                throw new NotFoundException("No message with the given id found",ErrorCodes.NOT_FOUND_ERROR);
            }
            return messageService.getDetailsById(id);
        }
        catch (Exception ex){
            throw new ServiceUnavailableException(ex.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }
    }

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


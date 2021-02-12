package com.notif.service.notif.controllers;


import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchByDateModel;
import com.notif.service.notif.models.request.SearchPhraseModel;
import com.notif.service.notif.services.ESService.ESService;
import com.notif.service.notif.utils.enums.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/search")
public class MessageESController {
    @Autowired
    private ESService messageESService;

    @GetMapping
    public Page<MessageESModel> getAll(){
        return messageESService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<MessageESModel> getById(@PathVariable String id){
        try{
            if(! messageESService.getById(id).isPresent()){
                throw new NotFoundException("No message with the given id found",ErrorCodes.NOT_FOUND_ERROR);
            }
            return  messageESService.getById(id);
        }
        catch (Exception ex){
            throw new ServiceUnavailableException(ex.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }
    }
    @PostMapping ("/phraseSearch")
    public Page<MessageESModel> getSmsByText(@RequestBody SearchPhraseModel phrase){
        return messageESService.getByText(phrase);
    }

    //    Get all sms sent to phone number between given start and end time
    @PostMapping
    public Page<MessageESModel> getSmsBetweenGivenDates(@RequestBody SearchByDateModel dateModel){
        return messageESService.getMsgBetweenDates(dateModel);
    }

}

package com.notif.service.notif.controllers;


import com.notif.service.notif.exception.NotFoundException;
import com.notif.service.notif.exception.ServiceUnavailableException;
import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchByDateModel;
import com.notif.service.notif.models.request.SearchPhraseModel;
import com.notif.service.notif.services.ESService.ESService;
import com.notif.service.notif.utils.enums.ErrorCodes;
import com.notif.service.notif.utils.enums.FailureEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/search")
public class MessageESController {
    Logger logger = LoggerFactory.getLogger(MessageESController.class);
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
                logger.error("ElasticSearch: "+FailureEnums.CANT_FIND.getMessage());
                throw new NotFoundException(FailureEnums.CANT_FIND.getMessage(), ErrorCodes.NOT_FOUND_ERROR);
            }
            return  messageESService.getById(id);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            throw new ServiceUnavailableException(ex.getMessage(),ErrorCodes.SERVICE_UNAVAILABLE_ERROR);
        }
    }
    @PostMapping ("/{text}/{page}/{size}")
    public Page<MessageESModel> getSmsByText(@PathVariable String text, @PathVariable int page, @PathVariable int size){
        logger.info("getSmsByText: text = {}, page = {}, size = {}",text,page,size);
        return messageESService.getByText(text, page, size);
    }
    @PostMapping ("phrase")
    public SearchPage<MessageESModel> getSmsByPhrase(@RequestBody SearchPhraseModel phrase){
        logger.info("getSmsByPhrase: text = {}, page = {}, size = {}",phrase.getPhrase(),phrase.getPage(),phrase.getSize());
        return messageESService.getByPhrase(phrase);
    }

    //    Get all sms sent to phone number between given start and end time
    @PostMapping
    public Page<MessageESModel> getSmsBetweenGivenDates(@RequestBody SearchByDateModel dateModel){
        logger.info("getSmsBetweenGivenDates: startDate = {}, endDate = {}", dateModel.getStartDate(),dateModel.getEndDate());
        return messageESService.getMsgBetweenDates(dateModel);
    }

}

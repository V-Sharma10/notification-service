package com.notif.service.notif.controllers;


import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.services.ESService.ESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/search")
public class MessageESController {
    @Autowired
    private ESService messageESService;

    @GetMapping
    public Iterable<MessageESModel> getAll(){
        return messageESService.getAll();
    }

    //    Get all sms sent to phone number between given start and end time
    @PostMapping
    public List<MessageESModel> getSmsBetweenGivenDates(@RequestBody String model){
        System.out.println(model);
        return null;
    }

}

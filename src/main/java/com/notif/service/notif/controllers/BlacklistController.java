package com.notif.service.notif.controllers;

import com.notif.service.notif.services.redisService.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/blacklist")
public class BlacklistController {
    Logger logger = LoggerFactory.getLogger(BlacklistController.class);
    @Autowired
    RedisService redisService;
//    get all
    @GetMapping("all")
    public Set getBlacklist(){
        logger.info("Get all blacklisted numbers.");
        return redisService.getAllBlacklistedNumbers();
    }

//    add to Blacklist set
    @PostMapping("/{number}")
    public String addToBlacklist(@PathVariable String number){
        logger.info("addToBlacklist: {}",number);
        return redisService.addToBlacklist(number);
    }

//    check if number exists in blacklist
    @PostMapping("check/{number}")
    public boolean checkIfExist(@PathVariable String number){
        logger.info("checkIfExist: {}",number);
        return redisService.checkIfExist(number);
    }


//    remove
    @DeleteMapping("delete/{number}")
    public String deleteFromBlacklist(@PathVariable String number){
        logger.info("deleteFromBlacklist: {}",number);
        return redisService.removeFromBlacklist(number);
    }
}

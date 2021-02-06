package com.notif.service.notif.controllers;

import com.notif.service.notif.services.redisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/blacklist")
public class BlacklistController {
    @Autowired
    RedisService redisService;
//    get all
    @GetMapping("all")
    public Set getBlacklist(){
        return redisService.getAllBlacklistedNumbers();
    }

//    add to Blacklist set
    @PostMapping("/{number}")
    public String addToBlacklist(@PathVariable String number){
        return redisService.addToBlacklist(number);
    }

//    check if number exists in blacklist
    @PostMapping("check/{number}")
    public boolean checkIfExist(@PathVariable String number){
        return redisService.checkIfExist(number);
    }


//    remove
    @DeleteMapping("delete/{number}")
    public String deleteFromBlacklist(@PathVariable String number){
        return redisService.removeFromBlacklist(number);
    }
}

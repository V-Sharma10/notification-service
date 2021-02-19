package com.notif.service.notif.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SecurityController {

    Logger logger = LoggerFactory.getLogger(SecurityController.class);
    @GetMapping("/auth-error")
    public ResponseEntity error(){
        logger.info("error method called");
        return new ResponseEntity("Authorization header is either wrong or missing.", HttpStatus.FORBIDDEN);
    }
}

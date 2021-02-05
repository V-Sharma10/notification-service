package com.notif.service.notif.exception;

import com.notif.service.notif.utils.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    private final ErrorCodes errorCode;

    public NotFoundException(String message, ErrorCodes errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCodes getCode() {
        return this.errorCode;
    }
}

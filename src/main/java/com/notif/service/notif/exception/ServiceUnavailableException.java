package com.notif.service.notif.exception;

import com.notif.service.notif.utils.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends RuntimeException{

    private final ErrorCodes errorCode;

    public ServiceUnavailableException(String message, ErrorCodes errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCodes getCode() {
        return this.errorCode;
    }
}

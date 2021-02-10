package com.notif.service.notif.exception;

import com.notif.service.notif.utils.enums.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import com.notif.service.notif.models.response.Error;
import java.util.Date;

@ControllerAdvice
@RestController
public class CustomExceptionController extends RuntimeException{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        Error exceptionResponse = new Error(new Date(), ErrorCodes.BAD_REQUEST_ERROR.getCode(),ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex, WebRequest request){
        Error exceptionResponse = new Error(new Date(),ex.getCode().getCode(),ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ServiceUnavailableException.class)
    public final ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailableException ex, WebRequest request){
        Error exceptionResponse = new Error(new Date(),ex.getCode().getCode(),ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }


    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex, WebRequest request){
        Error exceptionResponse = new Error(new Date(),ex.getCode().getCode(),ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}

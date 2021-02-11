package com.notif.service.notif.utils.enums;

public enum SuccessEnums {
    SUBMISSION_SUCCESS("Request Successfully Submitted.") ;

    private String message;

    SuccessEnums(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

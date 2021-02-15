package com.notif.service.notif.utils.enums;

public enum ValidatorEnums {
    PHONE_INVALID("Invalid Phone Number."),
    MESSAGE_INVALID("Invalid Message");

    private String message;

    ValidatorEnums(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

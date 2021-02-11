package com.notif.service.notif.utils.enums;

public enum FailureEnums {
    BLACKLIST("Blacklisted Number"),
    THIRD_API_FAIL("Third Party API failed"),
    CANT_FIND("Cannot find message");

    private String message;

    FailureEnums(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

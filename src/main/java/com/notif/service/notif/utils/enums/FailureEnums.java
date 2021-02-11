package com.notif.service.notif.utils.enums;

public enum FailureEnums {
    BLACKLIST("Blacklisted Number"),
    THIRD_API_FAIL("Third Party API failed"),
    CANT_FIND("Cannot find message"),
    REDIS_ADD_FAIL("Either Phone Number is not Valid or it has already been blacklisted."),
    FAILED("Failed");

    private String message;

    FailureEnums(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

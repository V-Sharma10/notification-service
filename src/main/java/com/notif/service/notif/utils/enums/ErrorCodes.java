package com.notif.service.notif.utils.enums;


public enum ErrorCodes {

    NOT_FOUND_ERROR(404),
    BAD_REQUEST_ERROR(400),
    FORBIDDEN_ERROR(403),
    SERVICE_UNAVAILABLE_ERROR(503);


    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

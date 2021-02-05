package com.notif.service.notif.utils;


public enum ErrorCodes {

    NOT_FOUND_ERROR(404),
    BAD_REQUEST_ERROR(400),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE_ERROR(503);


    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

package com.notif.service.notif.utils;

public enum StatusEnums {
    QUEUED(3),
    PROCESSING(2),
    SUCCESS(1),
    FAILED(0);

    private int code;

    StatusEnums(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}

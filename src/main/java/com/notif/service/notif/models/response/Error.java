package com.notif.service.notif.models.response;

import com.notif.service.notif.utils.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private Date time;
    private int code;
    private String message;
    private String details;


}

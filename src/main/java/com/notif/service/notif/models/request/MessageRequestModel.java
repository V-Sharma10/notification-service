package com.notif.service.notif.models.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestModel {
    private String phoneNumber;
    private String message;
}

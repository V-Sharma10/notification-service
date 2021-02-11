package com.notif.service.notif.models.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestModel {

    @NonNull
    @Pattern(regexp = "^[6-9]\\d{9}$")
    @Size(min = 10,max = 10)
    @NotBlank
    private String phoneNumber;

    @NonNull
    @NotBlank
    private String message;
}

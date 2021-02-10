package com.notif.service.notif.models.request;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

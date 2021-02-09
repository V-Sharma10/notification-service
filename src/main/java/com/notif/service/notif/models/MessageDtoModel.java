package com.notif.service.notif.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sms_request")
public class MessageDtoModel implements Serializable {

    @Id
    @Column(nullable = false)
    private String id;

    @NonNull
    @Pattern(regexp = "^[6-9]\\d{9}$")
    @Size(min = 10,max = 10)
    @NotBlank
    @Column(nullable = false)
    private String phoneNumber;

    @NotBlank
    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private int status;

    private int failureCode;

    private String failureComments;

    @Column(nullable = false)
    private Date createdAt=new Date();

    private Date updatedAt;

}

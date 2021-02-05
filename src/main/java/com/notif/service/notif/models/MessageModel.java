package com.notif.service.notif.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "sms_request")
public class MessageModel {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String status;

    private int failureCode;

    private String failureComments;

    @Column(nullable = false)
    private Date createdAt=new Date();

    private Date updatedAt;


}

package com.notif.service.notif.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "sms_service")
public class MessageESModel implements Serializable {
    @Id
    private String id;
    private String phoneNumber;
    private String message;
    private int status;
    private int failureCode;
    private String failureComment;
    private Date createdAt;
    private Date updatedAt;
}

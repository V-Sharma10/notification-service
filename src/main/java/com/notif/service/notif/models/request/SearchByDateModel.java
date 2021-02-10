package com.notif.service.notif.models.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchByDateModel {
//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private String startDate;

//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private String endDate;
}

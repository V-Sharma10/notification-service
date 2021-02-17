package com.notif.service.notif.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchByDateModel {
//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private String startDate;

//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private String endDate;

    private int page;
    private int size;
}

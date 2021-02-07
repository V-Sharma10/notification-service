package com.notif.service.notif.services.ESService;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchByDateModel;
import org.springframework.data.domain.Page;

public interface ESService {
    Page<MessageESModel> getAll();
    Page<MessageESModel> getMsgBetweenDates(SearchByDateModel dateModel);
}

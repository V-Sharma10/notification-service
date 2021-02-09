package com.notif.service.notif.services.ESService;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchByDateModel;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ESService {
    Optional<MessageESModel> getById(String id);
    Page<MessageESModel> getAll();
    Page<MessageESModel> getMsgBetweenDates(SearchByDateModel dateModel);
}

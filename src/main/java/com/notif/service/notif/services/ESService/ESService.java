package com.notif.service.notif.services.ESService;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchByDateModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface ESService {
    Optional<MessageESModel> getById(String id);
    Page<MessageESModel> getAll();
    Page<MessageESModel> getByText(String text);
    Page<MessageESModel> getMsgBetweenDates(SearchByDateModel dateModel);
}

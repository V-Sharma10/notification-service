package com.notif.service.notif.services.ESService;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchByDateModel;
import com.notif.service.notif.models.request.SearchPhraseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.SearchPage;

import java.util.Optional;

public interface ESService {
    Optional<MessageESModel> getById(String id);
    Page<MessageESModel> getAll();
    Page<MessageESModel> getByText(String text, int page, int size);
    SearchPage<MessageESModel> getByPhrase(SearchPhraseModel phrase);
    Page<MessageESModel> getMsgBetweenDates(SearchByDateModel dateModel);
}

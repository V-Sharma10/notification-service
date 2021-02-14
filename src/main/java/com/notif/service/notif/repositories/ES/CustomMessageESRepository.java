package com.notif.service.notif.repositories.ES;

import com.notif.service.notif.models.MessageESModel;
import com.notif.service.notif.models.request.SearchPhraseModel;
import org.springframework.data.elasticsearch.core.SearchPage;

public interface CustomMessageESRepository {
     SearchPage<MessageESModel> getByPhrase(SearchPhraseModel phrase);
}
